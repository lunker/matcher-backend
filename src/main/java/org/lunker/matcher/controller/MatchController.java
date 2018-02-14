package org.lunker.matcher.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.lunker.matcher.enums.Exercise;
import org.lunker.matcher.model.MatchRequest;
import org.lunker.matcher.repository.MatchRepository;
import org.lunker.matcher.repository.openapi.CityRepository;
import org.lunker.matcher.repository.openapi.GuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dongqlee on 2018. 2. 3..
 */

@Controller
@RequestMapping("/api/match")
@CrossOrigin(origins = "*")
public class MatchController {

    private Logger logger= LoggerFactory.getLogger(MatchController.class);

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private GuRepository guRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveMatchRequest(@RequestBody MatchRequest matchRequest){
        logger.info(matchRequest.toString());
        MatchRequest result=matchRepository.save(matchRequest);

        if(result.equals(matchRequest)){
            logger.info("Save Success");
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        else{
            logger.error("Save Error");
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/options", method = RequestMethod.GET)
    public ResponseEntity<String> getMatchOptions(){
        ResponseEntity<String> responseEntity=null;
        String result="no result ";

        JsonObject options=new JsonObject();
        JsonArray exerciseOptions=new JsonArray();
        JsonArray cityOptions=new JsonArray();
        JsonArray guOptions=new JsonArray();
        JsonObject tmp=new JsonObject();

        Gson gson = new Gson();

        for(Exercise exercise: Exercise.getExercise()){
            logger.info("[" +  "]" + exercise.getValue() + ":" + exercise.getMax());
            exerciseOptions.add(gson.toJsonTree(exercise));
        }
        options.add("exercise", exerciseOptions);

        cityRepository.findAll().forEach((city)->{
            cityOptions.add(gson.toJsonTree(city));
        });
        options.add("city", cityOptions);

        guRepository.findAll().forEach((gu)->{
            guOptions.add(gson.toJsonTree(gu));
        });
        options.add("gu", guOptions);


        result=gson.toJson(options);
        logger.info(result);

        responseEntity=new ResponseEntity<String>(result, HttpStatus.OK);

        return responseEntity;
    }
}
