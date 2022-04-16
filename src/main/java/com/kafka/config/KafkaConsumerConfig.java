package com.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.kafka.Model.Customer;
import com.kafka.constant.KafkaConstants;

@Configuration
public class KafkaConsumerConfig {
	@Bean
	public ConsumerFactory<String, Customer> consumerFactory(){
		Map<String, Object> configProp=new HashMap<String, Object>();
		//set the producer,Host, key and object as in map
		configProp.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,KafkaConstants.Host);
		configProp.put(ConsumerConfig.GROUP_ID_CONFIG,KafkaConstants.Group_id);
		configProp.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		configProp.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(configProp, new StringDeserializer(), new JsonDeserializer<>(Customer.class));
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Customer> kafkaListnerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, Customer> factory=new ConcurrentKafkaListenerContainerFactory<String, Customer>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
	
	
	
}
