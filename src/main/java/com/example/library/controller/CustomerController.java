package com.example.library.controller;

import com.example.library.converter.CustomerConverter;
import com.example.library.dto.CustomerDto;
import com.example.library.model.Customer;
import com.example.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "library/customer")
public class CustomerController
{
	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerConverter customerConverter;

	@GetMapping
	public List<Customer> getCustomers()
	{
		return customerService.getCustomers();
	}

	@GetMapping(value = "/{id}")
	public CustomerDto getCustomer(@PathVariable Long id)
	{
		return customerConverter.toDto(customerService.getCustomer(id));
	}

	@PostMapping
	public CustomerDto addCustomer(@Valid @RequestBody CustomerDto customerDto)
	{
		Customer customer = customerConverter.fromDto(customerDto);
		return customerConverter.toDto(customerService.addCustomer(customer));
	}

	@DeleteMapping(value = "/{customerId}")
	public CustomerDto deleteCustomer(@PathVariable Long customerId)
	{
		return customerConverter.toDto(customerService.deleteCustomer(customerId));
	}

	@PutMapping(value = "/{customerId}")
	public CustomerDto updateCustomer(@PathVariable Long customerId, @Valid @RequestBody CustomerDto customerDto)
	{
		Customer customer = customerConverter.fromDto(customerDto);
		return customerConverter.toDto(customerService.updateCustomer(customerId, customer));
	}
}
