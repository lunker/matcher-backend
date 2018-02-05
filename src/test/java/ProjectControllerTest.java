import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lunker.matcher.Application;
import org.lunker.matcher.model.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

/**
 * Created by dongqlee on 2018. 1. 8..
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

//    @Autowired
//    private MockMvc mvc;

    private URL base;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:8080");
    }

    @Test
    public void testCreateProject() throws Exception{
        ProjectEntity projectEntity=new ProjectEntity("test project", "https://github.com/lunker/matcher");

        ResponseEntity<Void> responseEntity=restTemplate.postForEntity("/api/project", projectEntity, null);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetProjectList() throws Exception{
        /*
        ResponseEntity<List<ProjectEntity>> responseEntity=restTemplate.getForObject("/api/project", ResponseEntity.class);

        Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        */

    }

    @Test
    public void testDeleteProjectList() throws Exception{

    }

    @Test
    public void testModifyProject() throws Exception{

    }
}
