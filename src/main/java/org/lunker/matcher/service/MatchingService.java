package org.lunker.matcher.service;

import org.lunker.matcher.entity.MatchRequest;
import org.lunker.matcher.repository.MatchRequestAreaRepository;
import org.lunker.matcher.repository.MatchRequestDateRepository;
import org.lunker.matcher.repository.MatchRequestMetaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by dongqlee on 2018. 2. 3..
 */
public class MatchingService {

    private Logger logger= LoggerFactory.getLogger(MatchingService.class);

    @Autowired
    private MatchRequestMetaRepository matchRequestMetaRepository;

    @Autowired
    private MatchRequestDateRepository matchRequestDateRepository;

    @Autowired
    private MatchRequestAreaRepository matchRequestAreaRepository;

    /*
    public void matching(List<MatchRequestArea> areaCandidates){
        List<MatchRequestArea> matchingCandidates=new ArrayList<>();
        int max=10;

        // city & gu로 들어온 MatchingRequest와 일치하는 stored request를 찾는다
        areaCandidates.forEach((areaCandidate)->{
            matchingCandidates.addAll(matchRequestAreaRepository.findByCityIdAndGuId(areaCandidate.getCityId(), areaCandidate.getGuId()));
        });
    }

    public void matching(MatchRequestBody matchRequestBody){

    }
    */

    public void matching(MatchRequest matchRequest){

    }

}

