package com.charter.nas.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
public class KafkaConsumerConfiguration {
	
	@Value("${spring.kafka.consumer.servers_config}")
	private String bootstrap_bootstrap_servers_config;
	 
	@Value("${spring.kafka.consumer.group_id_config}")	 
	private String group_id_config;
	
	@Value("${spring.kafka.consumer.enable_auto_commit_config}")
	private String enable_auto_commit_config;
	
	@Value("${spring.kafka.consumer.auto_commit_interval_ms_config}")
	private String auto_commit_interval_ms_config;
	
	@Value("${spring.kafka.consumer.session_timeout_ms_config}")
	private String session_timeout_ms_config;
	
	@Value("${spring.kafka.consumer.key_deserializer_class_config}")
	private String key_deserializer_class_config;
	
	@Value("${spring.kafka.consumer.value_deserializer_class_config}")
	private String value_deserializer_class_config;
	
	private Map<String, Object> consumerProps() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_bootstrap_servers_config);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, group_id_config);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enable_auto_commit_config);
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, auto_commit_interval_ms_config);
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, session_timeout_ms_config);
		
		try {
			props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, Class.forName(key_deserializer_class_config));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, Class.forName(value_deserializer_class_config));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
		//props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);		
		return props;
	}	

	@Bean
	public ConsumerFactory<Integer, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerProps());
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}
