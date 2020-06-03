package org.baeldung;

import org.cerner.SinkApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SinkApp.class)
public class SpringContextTest {

    @Test
    public void contextLoads() {
    }

}
