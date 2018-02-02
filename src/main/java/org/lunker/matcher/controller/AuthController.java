package org.lunker.matcher.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
}
