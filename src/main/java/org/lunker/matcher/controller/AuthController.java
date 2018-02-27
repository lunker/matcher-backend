package org.lunker.matcher.controller;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.lunker.matcher.model.OauthToken;
import org.lunker.matcher.repository.AuthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dongqlee on 2018. 1. 28..
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private Logger logger= LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthRepository authRepository;

    @RequestMapping("/login")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public String login(HttpServletRequest request){
        String url="https://github.com/login/oauth/authorize?client_id=99c43ae2d5370cd235ab";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);

        RestTemplate restTemplate=new RestTemplate();

        try{
            ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers), String.class);

            logger.info(response.getBody().toString());
            return response.getBody().toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:https://github.com/login/oauth/authorize?client_id=99c43ae2d5370cd235ab";
    }

    @RequestMapping(value = "/kakao/login", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> kakakoLogin(){
        String url="https://kauth.kakao.com/oauth/authorize?client_id=31724ccc00644d0a59c9a1f22dcaf4d8&redirect_uri=/oauth&response_type=code";

        ResponseEntity<?> responseEntity=null;

        try{
            CloseableHttpClient httpClient= HttpClients.createDefault();
            ResponseHandler<String> responseHandler=new BasicResponseHandler();

            HttpGet httpGet=new HttpGet(url);

            CloseableHttpResponse response=httpClient.execute(httpGet);
            int statusCode=response.getStatusLine().getStatusCode();

            if(statusCode == HttpStatus.OK.value()){
                String strResponseBody= EntityUtils.toString(response.getEntity());
                responseEntity=new ResponseEntity<String>(strResponseBody, HttpStatus.OK);
                logger.info(strResponseBody);
            }
            else{
                String reason=response.getStatusLine().getReasonPhrase();
                responseEntity=new ResponseEntity<String>(reason, HttpStatus.resolve(statusCode));
                logger.warn("Auth Failed: " + reason);
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            responseEntity=new ResponseEntity<String>("Failed Auth",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("Location", "https://kauth.kakao.com/oauth/authorize?client_id=31724ccc00644d0a59c9a1f22dcaf4d8&redirect_uri=/oauth&response_type=code");
        responseEntity=new ResponseEntity<Void>(httpHeaders, HttpStatus.TEMPORARY_REDIRECT);

        return responseEntity;
    }

    @RequestMapping(value = "/kakao/token", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Void> registerKakaoAccessToken(@RequestBody OauthToken oauthToken){
        logger.info("[/kakao/token]");

        logger.info(oauthToken.toString());
        authRepository.save(oauthToken);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/kakao/user", method = RequestMethod.GET, params = {"token"})
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> requestKakaoUserInfo(@RequestParam("token") String token){

        String url="https://kapi.kakao.com/v1/user/me";

        logger.info("Parameter token: " + token);
        ResponseEntity<String> responseEntity=null;

        try{
            CloseableHttpClient httpClient= HttpClients.createDefault();
            ResponseHandler<String> responseHandler=new BasicResponseHandler();

            HttpGet httpGet=new HttpGet(url);

            httpGet.addHeader("Authorization","Bearer " + token);
            httpGet.addHeader("Content-type" ,"application/x-www-form-urlencoded;charset=utf-8");


            CloseableHttpResponse response=httpClient.execute(httpGet);
//            String response=httpClient.execute(httpGet);
            int statusCode=response.getStatusLine().getStatusCode();

            if(statusCode == HttpStatus.OK.value()){
                String strResponseBody= EntityUtils.toString(response.getEntity());
                responseEntity=new ResponseEntity<String>(strResponseBody, HttpStatus.OK);
                logger.info(strResponseBody);
            }
            else{
                String reason=response.getStatusLine().getReasonPhrase();
                responseEntity=new ResponseEntity<String>(reason, HttpStatus.resolve(statusCode));
                logger.warn("Auth Failed: " + reason);
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            responseEntity=new ResponseEntity<String>("Failed Auth",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/kakao/token", method = RequestMethod.GET, params ={"uuid"})
    public ResponseEntity<?> proxyKakaoPushToken(@RequestParam(value = "uuid") int uuid){
        ResponseEntity<String> responseEntity=null;
        String kakaoTokenUrl="https://kapi.kakao.com/v1/push/tokens?uuid=" + uuid;
        String adminKey="8fe80aa113b4d4d758139697200be15d";

        try{

            CloseableHttpClient httpClient= HttpClients.createDefault();
            ResponseHandler<String> responseHandler=new BasicResponseHandler();

            HttpGet httpGet=new HttpGet(kakaoTokenUrl);

            httpGet.addHeader("Authorization","KakaoAK " + adminKey);
//            httpGet.addHeader("Content-Type", "application/json;charset=UTF-8");

            CloseableHttpResponse response=httpClient.execute(httpGet);
            int statusCode=response.getStatusLine().getStatusCode();

            if(statusCode == HttpStatus.OK.value()){
                String strResponseBody= EntityUtils.toString(response.getEntity());
                responseEntity=new ResponseEntity<String>(strResponseBody, HttpStatus.OK);
                logger.info(strResponseBody);
            }
            else{
                String reason=response.getStatusLine().getReasonPhrase();
                responseEntity=new ResponseEntity<String>(reason, HttpStatus.resolve(statusCode));
                logger.warn("Get kakao push token failed: " + reason);
            }

            return responseEntity;
        }
        catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            responseEntity=new ResponseEntity<String>("Failed get push kakao token",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping("/fb/callback")
    @ResponseBody
    public String fbLoginCallback(@RequestBody String requestBody){
        logger.info("/fb/callback");
        logger.info(requestBody);
        return "hi!";
    }
}
