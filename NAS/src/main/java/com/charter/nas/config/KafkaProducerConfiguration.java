package com.charter.nas.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfiguration {	
	
	@Value("${spring.kafka.producer.bootstrap_servers_config}")
	private String bootstrap_servers_config;	
	
	@Value("${spring.kafka.producer.retries_config}")
	private String retries_config;
	
	@Value("${spring.kafka.producer.batch_size_config}")
	private String batch_size_config;
	
	@Value("${spring.kafka.producer.linger_ms_config}")
	private String linger_ms_config;
	
	@Value("${spring.kafka.producer.buffer_memory_config}")
	private String buffer_memory_config;
	
	@Value("${spring.kafka.producer.key_serializer_class_config}")
	private String key_serializer_class_config;
	
	@Value("${spring.kafka.producer.value_serializer_class_config}")
	private String value_serializer_class_config;
	
	
	private Map<String, Object> producerProps() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers_config);
		props.put(ProducerConfig.RETRIES_CONFIG, retries_config);
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, batch_size_config);
		props.put(ProducerConfig.LINGER_MS_CONFIG, linger_ms_config);
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, buffer_memory_config);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, key_serializer_class_config);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, value_serializer_class_config);
		
		try {
			props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, Class.forName(key_serializer_class_config));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Class.forName(value_serializer_class_config));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
		//props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);		
		return props;
	}

	@Bean
	public KafkaTemplate<Integer, String> createTemplate() {
		Map<String, Object> senderProps = producerProps();
		ProducerFactory<Integer, String> pf = new DefaultKafkaProducerFactory<Integer, String>(senderProps);
		KafkaTemplate<Integer, String> template = new KafkaTemplate<>(pf);
		return template;
	}	

	

}
