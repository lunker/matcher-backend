package org.lunker.matcher.service;

import org.lunker.matcher.controller.MatchController;
import org.lunker.matcher.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by dongqlee on 2018. 2. 3..
 */
public class MatchingService {

    private Logger logger= LoggerFactory.getLogger(MatchingService.class);

    @Autowired
    private ProjectRepository projectRepository;



}
