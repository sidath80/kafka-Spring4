package com.codenotfound.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainConsumer {
	
 public static void main(String args[]) throws Exception {
		 
	 		ApplicationContext context = new AnnotationConfigApplicationContext(ReceiverConfig.class);
	
	 }


}
