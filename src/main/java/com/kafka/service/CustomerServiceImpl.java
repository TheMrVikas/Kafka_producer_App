package com.kafka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kafka.Model.Customer;
import com.kafka.constant.KafkaConstants;

/**
 * 
 * @author Vikas
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	//inject Kafka Template
	@Autowired
	private KafkaTemplate<String, Customer> kafkaTemplate;
	
	public String addCustomer(List<Customer>customer) {
		if(!customer.isEmpty()) {
			for(Customer c:customer) {
			kafkaTemplate.send(KafkaConstants.Topic, c);
			System.out.println("************* Message Published ******************");
			}
		}
		return "Customer Record added to Kafka Successfully";
		
	}
	
	@KafkaListener(topics = KafkaConstants.Topic, groupId = KafkaConstants.Group_id)
	public Customer listner(Customer c) {
		System.out.println("************ message Recived ************ ");
		return c;
	}
}
