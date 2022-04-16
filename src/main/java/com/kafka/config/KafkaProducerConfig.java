package com.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.kafka.Model.Customer;
import com.kafka.constant.KafkaConstants;

/**
 * 
 * @author Vikas
 *
 */
@Configuration
public class KafkaProducerConfig {
	
	//create producer factory
	@Bean
	public ProducerFactory<String, Customer> producerFactory(){
		Map<String, Object> configProp=new HashMap<String, Object>();
		//set the producer,Host, key and object as in map
		configProp.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,KafkaConstants.Host);
		configProp.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
		configProp.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
		return new DefaultKafkaProducerFactory<String, Customer>(configProp);
	}
	
	/**
	 * This method is used to create KafkaTemplate bean object
	 * @return
	 */
	@Bean
	public KafkaTemplate<String , Customer> kafkaTemplate(){
		return new KafkaTemplate<String, Customer>(producerFactory());
	}
}
