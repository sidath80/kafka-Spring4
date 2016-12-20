package com.mycompany.app;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;

public class SpringConsumer {
	
	   private static final Logger logger = LoggerFactory.getLogger(SpringConsumer.class);

	
	   public static void main(String[] args) {
		   
		   
		/*logger.info("Start auto");
		ContainerProperties containerProps = new ContainerProperties("test");
		KafkaMessageListenerContainer<Integer, String> container = createContainer(containerProps);
		final CountDownLatch latch = new CountDownLatch(4);
		containerProps.setMessageListener(new MessageListener<Integer, String>() {

			public void onMessage(ConsumerRecord<Integer, String> message) {
				logger.info("received: " + message);
				latch.countDown();
			}

		});
		container.setBeanName("testAuto");
		container.start();*/
		
		   logger.info("Start auto");
		    ContainerProperties containerProps = new ContainerProperties("topic1", "topic2");
		    KafkaMessageListenerContainer<Integer, String> container = createContainer(containerProps);
		    final CountDownLatch latch = new CountDownLatch(4);
		    containerProps.setMessageListener(new MessageListener<Integer, String>() {

		        @Override
		        public void onMessage(ConsumerRecord<Integer, String> message) {
		            logger.info("received: " + message);
		            latch.countDown();
		        }

		    });
		    container.setBeanName("testAuto");
		    container.start();
		
		   
	   }
	   
	   private static KafkaMessageListenerContainer<Integer, String> createContainer(ContainerProperties containerProps) {
			/*Map<String, Object> props = consumerProps();
			DefaultKafkaConsumerFactory<Integer, String> cf = new DefaultKafkaConsumerFactory<Integer, String>(props);
			KafkaMessageListenerContainer<Integer, String> container = new KafkaMessageListenerContainer<>(cf,
					containerProps);
			return container;*/
			
			Map<String, Object> props = consumerProps();
		    DefaultKafkaConsumerFactory<Integer, String> cf =
		                            new DefaultKafkaConsumerFactory<Integer, String>(props);
		    KafkaMessageListenerContainer<Integer, String> container =
		                            new KafkaMessageListenerContainer<>(cf, containerProps);
		    return container;
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
