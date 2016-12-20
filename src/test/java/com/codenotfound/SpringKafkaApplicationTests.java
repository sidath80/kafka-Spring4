package com.codenotfound;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.codenotfound.producer.Sender;
import com.codenotfound.producer.SenderConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SenderConfig.class,loader = AnnotationConfigContextLoader.class)
public class SpringKafkaApplicationTests {

    @Autowired
    private Sender sender;


    @Test
    public void testReceiver() throws Exception {
        sender.sendMessage("test", "Hello Spring Kafka!");

       // receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
       /// assertThat(receiver.getLatch().getCount()).isEqualTo(0);
    }
}
