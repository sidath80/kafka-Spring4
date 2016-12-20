package com.mycompany.app;

import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;

public class Listener {
	
	     final CountDownLatch latch1 = new CountDownLatch(1);

	    @KafkaListener(id = "foo", topics = "test")
	    public void listen1(String foo) {
	        this.latch1.countDown();
	    }


}
