package match;

import org.junit.Test;
import org.lunker.matcher.service.HttpService;

/**
 * Created by dongqlee on 2018. 3. 10..
 */
public class MatchTestRest {

    @Test
    public void testMatching(){

        try{
            String response=HttpService.get("http://localhost:8080/api/match", null);
            System.out.println(response);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
