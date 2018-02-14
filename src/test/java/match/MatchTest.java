package match;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lunker.matcher.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

/**
 * Created by dongqlee on 2018. 1. 8..
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MatchTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private URL base;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:8080");
    }

    @Test
    public void testGetMatchingOptions(){
        ResponseEntity<String> responseEntity=restTemplate.getForEntity("/api/match/options", String.class);
        String responseBody=responseEntity.getBody();

        System.out.println(responseBody);
    }
}
