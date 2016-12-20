package com.mycompany.app;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;

public class KConsumer {

	private static final Logger log = LoggerFactory.getLogger(KConsumer.class);

	@Autowired
	MessageChannel kafkaService;

	public void processMessage(Map<String, Map<Integer, List<byte[]>>> msgs) {
		for (Map.Entry<String, Map<Integer, List<byte[]>>> entry : msgs.entrySet()) {
			log.debug("Topic:" + entry.getKey());
			ConcurrentHashMap<Integer, List<byte[]>> messages = (ConcurrentHashMap<Integer, List<byte[]>>) entry
					.getValue();
			log.debug("\n**** Partition: \n");
			Set<Integer> keys = messages.keySet();
			for (Integer i : keys)
				log.debug("p:" + i);
			log.debug("\n**************\n");
			Collection<List<byte[]>> values = messages.values();
			for (Iterator<List<byte[]>> iterator = values.iterator(); iterator.hasNext();) {
				List<byte[]> list = iterator.next();
				for (byte[] object : list) {
					String message = new String(object);
					log.debug("Message: " + message);
					try {
						//kafkaService.
					} catch (Exception e) {
						log.error(String.format("Failed to process message %s", message));
					}
				}
			}

		}
	}
}