package com.mycompany.app;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;

public class SpringKafka10Consumer {
	
	 private static final String TEMPLATE_TOPIC = "test";

	    public static void main(String args[]) throws Exception {
	        
	    	Map<String, Object> consumerProps = consumerProps();
	        DefaultKafkaConsumerFactory<Integer, String> cf =
	                            new DefaultKafkaConsumerFactory<Integer, String>(consumerProps);
	        ContainerProperties containerProperties = new ContainerProperties(TEMPLATE_TOPIC);
	        KafkaMessageListenerContainer<Integer, String> container =
	                            new KafkaMessageListenerContainer<>(cf, containerProperties);
	        final BlockingQueue<ConsumerRecord<Integer, String>> records = new LinkedBlockingQueue<>();
	        container.setupMessageListener(new MessageListener<Integer, String>() {

	        	@Override
	        	public void onMessage(ConsumerRecord<Integer, String> record) {
	                System.out.println(record);
	                records.add(record);
	            }

	        });
	        container.setBeanName("templateTests");
	        container.start();

	    }
	   
	   private static Map<String, Object> consumerProps() {
			Map<String, Object> props = new HashMap<>();
			props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			props.put(ConsumerConfig.GROUP_ID_CONFIG, "g5");
			props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
			props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
			props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
			props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
			props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
			return props;
		}


}
