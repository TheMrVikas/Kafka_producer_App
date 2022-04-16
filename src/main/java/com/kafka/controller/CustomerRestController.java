package com.kafka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.Model.Customer;
import com.kafka.service.CustomerService;

@RestController
@RequestMapping(value = {"/kafka"})
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;
	//add customer info
	
	@GetMapping("/")
	public String getWelcomeMsg() {
		return "Welcome To Apache Kafka Application";
	}
	
	@PostMapping(value = "/addcustomer",consumes = MediaType.APPLICATION_JSON_VALUE,
										produces =MediaType.APPLICATION_JSON_VALUE )
	public String addCustomer(@RequestBody List<Customer> customer) {
	return customerService.addCustomer(customer);
	}
	
}
