package com.kafka.service;

import java.util.List;

import com.kafka.Model.Customer;

public interface CustomerService {
	public String addCustomer(List<Customer>customer);
}
