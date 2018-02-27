package match;

import org.junit.runner.RunWith;
import org.lunker.matcher.Application;
import org.lunker.matcher.model.Area;
import org.lunker.matcher.model.MatchRequestBody;
import org.lunker.matcher.model.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongqlee on 2018. 2. 17..
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequestGenerator {

    private Logger logger= LoggerFactory.getLogger(RequestGenerator.class);

    private int requests=10000;
    private int maxCity=17;
    private int maxGu=249;

    @Autowired
    private TestRestTemplate restTemplate;

    public void generate(){

        List<Area> areaList=new ArrayList<>();
        areaList.add(new Area(randCity(),randGu()));
        areaList.add(new Area(randCity(),randGu()));
        areaList.add(new Area(randCity(),randGu()));

        List<TimeZone> dateList=new ArrayList<>();
        dateList.add(randDate());
        dateList.add(randDate());

        MatchRequestBody data=new MatchRequestBody();
        data.setExerciseId(1);
        data.setMatchingDateCandidates(dateList);
        data.setAreaCandidates(areaList);
    }

    public void sendRequest(){
        logger.info("Generate Match request");

        ResponseEntity<String> responseEntity=restTemplate.postForEntity("/api/match", null, String.class);
        String responseBody=responseEntity.getBody();

        System.out.println(responseBody);
    }

    public void createMatchRequest(){

    }

    private int randCity(){
        return ( (int) Math.random() * maxCity) + 1;
    }

    private int randGu(){
        return ( (int) Math.random() * maxGu) + 1;
    }

    private TimeZone randDate(){
        LocalDateTime from=LocalDateTime.now().plusHours(2);
        LocalDateTime to=from.plusHours( ((int) Math.random() * 8) + 1);

        return new TimeZone(from, to);
    }
}
