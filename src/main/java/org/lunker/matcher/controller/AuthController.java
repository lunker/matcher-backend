package org.lunker.matcher.controller;

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
@RequestMapping("/auth")
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

    @RequestMapping(value = "/kakao/login", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Void> kakaoLogin(@RequestBody OauthToken oauthToken){
        logger.info("[/kakao/login]");

        logger.info(oauthToken.toString());

        authRepository.save(oauthToken);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
