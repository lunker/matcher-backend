package org.lunker.matcher.controller;

import org.lunker.matcher.model.MatchRequest;
import org.lunker.matcher.repository.MatchRepository;
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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> add(@RequestBody MatchRequest matchRequest){


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
}
