package com.mycompany.app;



public class SpringKafka10Producer {
   public static void main(String[] args) {
	
      KProducer  pp=new KProducer();
      try {
		pp.sendMessage("test", "sidath");
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
   }
}