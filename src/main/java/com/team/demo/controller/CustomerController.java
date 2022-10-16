package com.team.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.management.AttributeNotFoundException;
import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.team.demo.entity.Customer;
import com.team.demo.exception.CustomerDoesNotExistException;
import com.team.demo.service.CustomerService;

@RestController
public class CustomerController 
{
	@Autowired
	CustomerService customerService;
	@PostMapping("/customer")
	public Customer saveCustomer(@RequestBody Customer customer) 
	{
		return customerService.createCustomer(customer);
	}
	
	@GetMapping("/customers")
	public List<Customer> getCustomers()
	{
		return customerService.getCustomers();
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<Optional<Customer>> getCustomer(@PathVariable("id") int id) 
	{
		return customerService.getCustomer(id);
		
	}
	@PutMapping("/updateCust")
	public String update(@RequestBody Customer customer) throws CustomerDoesNotExistException 
	{
		return customerService.updateCustomer(customer);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> removeCustomer(@PathVariable("id")int id) 
	{
		return customerService.deleteCustomer(id);
	}
}	
	