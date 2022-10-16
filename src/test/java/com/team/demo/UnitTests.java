package com.team.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.team.demo.Repository.CustomerRepository;
import com.team.demo.entity.Customer;

@SpringBootTest
public class UnitTests 
{

	@Autowired
	CustomerRepository customerRepository;

	@Test
	public void TestAdd() 
	{
		Customer customer = new Customer();
		customer.setId(5);
		customer.setAccount("FD");
		customer.setName("Patel");
		customerRepository.save(customer);
		assertNotNull(customerRepository.findById(5).get());
	}
	@Test
	public void TestGetAll() 
	{
		List<Customer> customers = customerRepository.findAll();
		assertThat(customers).size().isGreaterThan(0);
	}
	@Test
	public void TestGet() 
	{
		Customer customer = customerRepository.findById(2).get();
		assertEquals("Shah",customer.getName());
	}
	@Test
	public void TestUpdate() 
	{
		Customer customer = customerRepository.findById(2).get();
		customer.setName("Panchal");
		customerRepository.save(customer);
		assertNotEquals("Shah", customerRepository.findById(2).get().getName());

	}
	@Test
	public void TestDelete() 
	{
		customerRepository.deleteById((1));
		assertThat(customerRepository.existsById(1)).isFalse();
	}
}
