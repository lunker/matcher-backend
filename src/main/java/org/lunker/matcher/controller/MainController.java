package org.lunker.matcher.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dongqlee on 2018. 1. 9..
 */
@Controller
@EnableAutoConfiguration
public class MainController {

    private Logger logger= LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/")
    String hello() {
        logger.info("url :: /" );
        return "index.html";
    }
}
