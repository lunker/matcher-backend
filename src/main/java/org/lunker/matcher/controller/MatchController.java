package org.lunker.matcher.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.lunker.matcher.entity.MatchRequestArea;
import org.lunker.matcher.entity.MatchRequestDate;
import org.lunker.matcher.entity.MatchRequestMetadata;
import org.lunker.matcher.model.MatchRequestBody;
import org.lunker.matcher.repository.ExerciseRepository;
import org.lunker.matcher.repository.MatchRequestAreaRepository;
import org.lunker.matcher.repository.MatchRequestDateRepository;
import org.lunker.matcher.repository.MatchRequestMetaRepository;
import org.lunker.matcher.repository.openapi.CityRepository;
import org.lunker.matcher.repository.openapi.GuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dongqlee on 2018. 2. 3..
 */

@Controller
@RequestMapping("/api/match")
@CrossOrigin(origins = "*")
public class MatchController {
    private Logger logger= LoggerFactory.getLogger(MatchController.class);

    @Autowired
    private MatchRequestMetaRepository matchRequestMetaRepository;

    @Autowired
    private MatchRequestDateRepository matchRequestDateRepository;

    @Autowired
    private MatchRequestAreaRepository matchRequestAreaRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private GuRepository guRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveMatchRequest(@RequestBody MatchRequestBody matchRequestBody){
        logger.info(matchRequestBody.toString());

        MatchRequestMetadata metadata=MatchRequestMetadata.exchange(matchRequestBody);
        MatchRequestMetadata result=matchRequestMetaRepository.save(metadata);

        if(result.equals(metadata)){
            logger.info("Save Success! Result:\n" + result.toString());

            matchRequestBody.getAreaCandidates().stream().forEach((area)->{
                logger.info(area.toString());
                MatchRequestArea areaMapping=new MatchRequestArea(result.getId(), area.getCityId(), area.getGuId());
                matchRequestAreaRepository.save(areaMapping);
            });

            matchRequestBody.getMatchingDateCandidates().stream().forEach((matchingDate)->{
                logger.info(matchingDate.toString());
                MatchRequestDate dateMapping=new MatchRequestDate(result.getId(), matchingDate.getFromMatchingDate(), matchingDate.getToMatchingDate());
                matchRequestDateRepository.save(dateMapping);
            });

            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        else{
            logger.error("Save Error");
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/options", method = RequestMethod.GET)
    @ResponseBody
    public String getMatchOptions(){
        ResponseEntity<String> responseEntity=null;
        String result="no result ";

        JsonObject options=new JsonObject();
        JsonArray exerciseOptions=new JsonArray();
        JsonArray cityOptions=new JsonArray();
        JsonObject guOptions=new JsonObject();

        JsonObject tmp=new JsonObject();

        Gson gson = new Gson();

        exerciseRepository.findAll().forEach((exercise)->{
            JsonObject tmpExercise=(JsonObject) gson.toJsonTree(exercise);
            tmpExercise.addProperty("text", exercise.getName());
            tmpExercise.addProperty("value", exercise.getName());
            tmpExercise.addProperty("key", exercise.getName());
            exerciseOptions.add(gson.toJsonTree(tmpExercise));
        });

        options.add("exercise", exerciseOptions);

        cityRepository.findAll().forEach((city)->{
            JsonObject tmpCity=(JsonObject) gson.toJsonTree(city);
            tmpCity.addProperty("text", city.getCityName());
            tmpCity.addProperty("value", city.getCityName());
            tmpCity.addProperty("key", city.getCityName());
            cityOptions.add(tmpCity);
        });

        options.add("city", cityOptions);

        guRepository.findAll().forEach((gu)->{
            JsonObject tmpGu=(JsonObject) gson.toJsonTree(gu);
            tmpGu.addProperty("text", gu.getGuCode());
            tmpGu.addProperty("value", gu.getGuCode());
            tmpGu.addProperty("key", gu.getGuCode());

            if(guOptions.has(gu.getCityCode())) {
                // parent city array is exist
                guOptions.getAsJsonArray(gu.getCityCode()).add(tmpGu);
            }
            else{
                JsonArray parentArr=new JsonArray();
                parentArr.add(tmpGu);
                guOptions.add(gu.getCityCode(), parentArr);
            }
        });

        options.add("gu", guOptions);

        result=gson.toJson(options);
        logger.info(result);

        responseEntity=new ResponseEntity<String>(result, HttpStatus.OK);

//        return responseEntity;
        return result;
    }


    @RequestMapping("/send")
    public ResponseEntity<String> testSend(@RequestParam("token") String token){
        logger.info("Test send message");
        logger.info(token);
        String endpoint="https://kapi.kakao.com/v2/api/talk/memo/default/send";

        ResponseEntity<String> responseEntity=null;

        JsonObject postData=new JsonObject();
        JsonObject templateObject=new JsonObject();
        JsonObject contentObject=new JsonObject();

        try{
            String url=endpoint;

            CloseableHttpClient httpClient= HttpClients.createDefault();
            ResponseHandler<String> responseHandler=new BasicResponseHandler();

            HttpPost httpPost=new HttpPost(url);


            contentObject.addProperty("title","test!");
            contentObject.addProperty("image_url", "not valid url");

            templateObject.addProperty("object_type", "feed");
            templateObject.add("content", contentObject);

            postData.add("template_object", templateObject);

            logger.info(postData.toString());


            StringEntity requestEntity = new StringEntity(
                    postData.toString(),
                    ContentType.APPLICATION_JSON);
            httpPost.setEntity(requestEntity);

            httpPost.addHeader("Authorization","Bearer " + token);
            httpPost.addHeader("Content-type", "application/json");
//            httpPost.addHeader("Content-type" ,"application/x-www-form-urlencoded;charset=utf-8");


            CloseableHttpResponse response=httpClient.execute(httpPost);
//            String response=httpClient.execute(httpGet);
            int statusCode=response.getStatusLine().getStatusCode();

            if(statusCode == HttpStatus.OK.value()){
                String strResponseBody= EntityUtils.toString(response.getEntity());

                logger.info(strResponseBody);

                responseEntity=new ResponseEntity<String>(strResponseBody, HttpStatus.OK);
                logger.info(strResponseBody);
            }
            else{
                String strResponseBody= EntityUtils.toString(response.getEntity());
                String reason=response.getStatusLine().getReasonPhrase();


                responseEntity=new ResponseEntity<String>(reason, HttpStatus.resolve(statusCode));
                logger.warn("Auth Failed: " + reason);
                logger.warn(strResponseBody);
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            responseEntity=new ResponseEntity<String>("Failed Auth",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
