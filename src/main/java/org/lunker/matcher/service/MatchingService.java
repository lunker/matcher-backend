package org.lunker.matcher.service;

import org.lunker.matcher.entity.MatchRequest;
import org.lunker.matcher.entity.MatchRequestMetadata;
import org.lunker.matcher.model.Area;
import org.lunker.matcher.model.TimeZone;
import org.lunker.matcher.repository.MatchRequestAreaRepository;
import org.lunker.matcher.repository.MatchRequestDateRepository;
import org.lunker.matcher.repository.MatchRequestMetaRepository;
import org.lunker.matcher.repository.MatchRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private MatchRequestRepository matchRequestRepository;

    public void matching(List<TimeZone> timeZoneList, List<Area> areaList){
        logger.info("Find proper matching request");

        List<MatchRequest> matchingCandidates=new ArrayList<>();

        // 매칭 요청과 같은 지역내에 있는 MatchRequest들을 Gatehring
        areaList.stream().forEach((area)->{
            matchingCandidates.addAll(matchRequestRepository.findByCityIdAndGuId(area.getCityId(), area.getGuId()));
        });

        // Filter:: by matchingFromDate, matchingToDate
        matchingCandidates.stream().filter((storedRequest)->{
            for(TimeZone requestedTimezone : timeZoneList){
                if(storedRequest.getFromDate().compareTo(requestedTimezone.getFromMatchingDate()) > 0  && storedRequest.getToDate().compareTo(requestedTimezone.getToMatchingDate()) <0){
                    return true;
                }
            }
            return false;
        }).forEach((filteredCandidates)->{
            logger.info(filteredCandidates.toString());
        });
    }

    public void matching(){
        List<MatchRequestMetadata> unMatchedRequestMetadataList=matchRequestMetaRepository.findByEqualsIsComplete(false);
    }
}

