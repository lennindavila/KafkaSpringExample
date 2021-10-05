package com.charter.nas.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.charter.nas.bd1.models.entity.Table1_db1;
import com.charter.nas.bd2.models.entity.Table2_db2;

@Component
public class NasListener {

	private static final Logger log = LoggerFactory.getLogger(NasListener.class);
	
	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;
	
	@Autowired
	private ITable1_db1 table1_db1Service;
	
	@Autowired
	private ITable2_db2 table2_db2Service;

	@KafkaListener(topics = "devs4j-topic", groupId = "devs4js-group")
	public void listen(String message) {
		log.info("Message from Kafka: {}", message);		
		/*
		List<Table1_db1> lista = table1_db1Service.findAll();		
		for (Table1_db1 item : lista) {
			String msg = item.getId() + "//" + item.getName() + ": " + message;
			kafkaTemplate.send("devs4j-topic",msg);
			table2_db2Service.save(new Table2_db2(msg));
		}
		*/
		
		//kafkaTemplate.send("devs4j-topic", message + "procesado");		
		/*try {
			Thread.sleep(5000l);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}*/
	}
}
