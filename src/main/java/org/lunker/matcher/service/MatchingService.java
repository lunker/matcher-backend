package org.lunker.matcher.service;

import org.lunker.matcher.entity.MatchRequest;
import org.lunker.matcher.entity.MatchRequestMetadata;
import org.lunker.matcher.model.Area;
import org.lunker.matcher.model.TimeZone;
import org.lunker.matcher.repository.MatchRequestAreaRepository;
import org.lunker.matcher.repository.MatchRequestDateRepository;
import org.lunker.matcher.repository.MatchRequestMetaRepository;
import org.lunker.matcher.repository.MatchRequestRepository;
import org.lunker.matcher_common.exception.InstanceNotFoundException;
import org.lunker.matcher_common.model.Constants;
import org.lunker.matcher_common.util.DistanceMatrix;
import org.lunker.matcher_common.util.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dongqlee on 2018. 2. 3..
 */
@Service
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

    public List<MatchRequest> matching(int exerciseId, List<TimeZone> timeZoneList, List<Area> areaList){
        logger.info("Find proper matching request");
        LocalDateTime start=LocalDateTime.now();

        List<MatchRequest> results=null;
        List<MatchRequest> storedRequests=new ArrayList<>();
        List<Integer> guIds=new ArrayList<>();
        List<Long> availableMetaRequests=matchRequestMetaRepository.findByExerciseIdEqualsAndIsCompleteEquals(exerciseId, false);

        logger.info("1st step: " + Duration.between(start, LocalDateTime.now()).toMillis()/1000.0);
        start=LocalDateTime.now();

        areaList.stream().forEach((area)->{
            guIds.add(area.getGuId());
        });

        // 매칭 요청과 같은 지역내에 있는 MatchRequest들을 Gatehring
        // 시간 조따 걸림
        storedRequests=matchRequestRepository.findByReqIdInAndGuIdIn(availableMetaRequests, guIds);

        logger.info("2nd step: " + Duration.between(start, LocalDateTime.now()).toMillis()/1000.0);
        start=LocalDateTime.now();


        results=storedRequests.stream().filter((storedRequest)->{
            for(TimeZone requestedTimezone : timeZoneList){
                if(storedRequest.getFromDate().compareTo(requestedTimezone.getFromMatchingDate()) == 0  &&
                        storedRequest.getStartHour() == requestedTimezone.getFromMatchingHour() ){
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());

        logger.info("3rd step: " + Duration.between(start, LocalDateTime.now()).toMillis()/1000.0);

        return results;
    }

    public void matchingv2(){List<MatchRequest> matchingCandidates=new ArrayList<>();

        try{
            DistanceMatrix<Integer, Integer> distanceMatrix=(DistanceMatrix<Integer, Integer>) ObjectFactory.getInstance().getObject(Constants.DISTANCE_KEY);
        }
        catch (InstanceNotFoundException inf){
            inf.printStackTrace();
            logger.error(inf.getMessage());
        }

        /*
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
        */

    }

    public void matching(){
        List<MatchRequestMetadata> unMatchedRequestMetadataList=matchRequestMetaRepository.findByIsCompleteEquals(false);
    }
}

