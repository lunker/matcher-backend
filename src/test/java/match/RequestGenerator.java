package match;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.lunker.matcher.model.Area;
import org.lunker.matcher.model.MatchRequestBody;
import org.lunker.matcher.model.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongqlee on 2018. 2. 17..
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequestGenerator {

    private Logger logger= LoggerFactory.getLogger(RequestGenerator.class);

    private int requests=10000;
    private int maxCity=17;
    private int maxGu=249;

    /*
    @Autowired
    private TestRestTemplate restTemplate;
    */

    public void generate(){
        MatchRequestBody body=null;
        for(int idx=0; idx<requests; idx++){
            body=createMatchRequest();
            System.out.println("Generated request data:\n" + body.toString());
            sendRequest(body);
        }
    }

    private void sendRequest(MatchRequestBody matchRequestBody){
        logger.info("Generate Match request");

        String url="http://localhost:8080/api/match";

        try {

            CloseableHttpClient httpClient= HttpClients.createDefault();
            HttpPost httpPost=new HttpPost(url);
            httpPost.setEntity(new StringEntity(matchRequestBody.toString(), ContentType.APPLICATION_JSON));

            CloseableHttpResponse httpResponse=httpClient.execute(httpPost);
            String responseBody= EntityUtils.toString(httpResponse.getEntity());

//            ResponseEntity<String> responseEntity=restTemplate.postForEntity("/api/match", matchRequestBody, String.class);
            System.out.println(responseBody);
        }
        catch (Exception e){

        }

    }

    private MatchRequestBody createMatchRequest(){
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

        return data;
    }

    private int randCity(){
        return  (int) ( Math.random() * maxCity) + 1;
    }

    private int randGu(){
        return (int)( Math.random() * maxGu) + 1;
    }

    private TimeZone randDate(){
        LocalDateTime from=LocalDateTime.now().plusHours(2);
        LocalDateTime to=from.plusHours( (int)( Math.random() * 18) + 1);

        return new TimeZone(from, to);
    }
}
