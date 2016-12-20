package com.mycompany.app;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import junit.framework.TestCase;

public class MainL extends TestCase {
	
	
	@Autowired
	private Listener listener;

	@Autowired
	private static KafkaTemplate<Integer, String> template;

	
	public void testSimple() throws Exception {
	    //waitListening("foo");
	    template.send("test", 0, "foo");
	    assertTrue(this.listener.latch1.await(10, TimeUnit.SECONDS));
	}
	

}
