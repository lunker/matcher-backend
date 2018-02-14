package address;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lunker.matcher.Application;
import org.lunker.matcher.service.AddressHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by dongqlee on 2018. 2. 14..
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressTest {

    @Autowired
    private AddressHandler addressHandler;

    @Test
    public void testCity(){
        addressHandler.read1stDepth();
    }

    @Test
    public void testGU(){
        addressHandler.read2ndDepth();
    }
}

