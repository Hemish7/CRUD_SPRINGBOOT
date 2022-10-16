package com.team.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.team.demo.Repository.CustomerRepository;
import com.team.demo.entity.Customer;
import com.team.demo.exception.CustomerDoesNotExistException;

import ch.qos.logback.core.joran.conditional.ThenOrElseActionBase;

@Component
public class CustomerService 
{
	@Autowired
	CustomerRepository customerRepository;
	
	public Customer createCustomer(Customer customer) 
	{
		return customerRepository.save(customer);
	}
	
	public List<Customer> getCustomers()
	{
		return customerRepository.findAll();
		
	}
	
	public ResponseEntity<Optional<Customer>> getCustomer(int id) 
	{
		boolean CustomerExists = customerExists(id);
		if(CustomerExists) 
		{
			Optional<Customer> customer = customerRepository.findById(id);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer does not exist");

		}
	}
	
	public String updateCustomer(Customer customer) throws CustomerDoesNotExistException 
	{
		Customer existingCustomer = customerRepository.findById(customer.getId()).orElse(null);
		if(existingCustomer == null) 
		{
			throw new CustomerDoesNotExistException("Customer does not exist");
		}
		else 
		{
			existingCustomer.setName(customer.getName());
			existingCustomer.setId(customer.getId());
			existingCustomer.setAccount(customer.getAccount());
			customerRepository.save(existingCustomer);
			return "Customer Details updated";
		}
	}
	
	public ResponseEntity<HttpStatus> deleteCustomer(int id) 
	{
		boolean CustomerExists = customerExists(id);
		if(CustomerExists) 
		{
			customerRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else 
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	public boolean customerExists(int id)
	{
		return customerRepository.existsById(id);
		
	}
}
