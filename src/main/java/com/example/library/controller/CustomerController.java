package com.example.library.controller;

import com.example.library.converter.BorrowedConverter;
import com.example.library.converter.CustomerConverter;
import com.example.library.converter.CycleAvoidingMappingContext;
import com.example.library.dto.BorrowedDto;
import com.example.library.dto.CustomerDto;
import com.example.library.model.Borrowed;
import com.example.library.model.Customer;
import com.example.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "library/customer")
public class CustomerController
{
	private CustomerService customerService;

	private CustomerConverter customerConverter;

	private BorrowedConverter borrowedConverter;

	@Autowired
	public CustomerController(CustomerService customerService, CustomerConverter customerConverter,
							  BorrowedConverter borrowedConverter)
	{
		this.customerService = customerService;
		this.customerConverter = customerConverter;
		this.borrowedConverter = borrowedConverter;
	}

	@GetMapping
	public List<CustomerDto> getCustomers()
	{
		List<Customer> customers = customerService.getCustomers();
		return customers.stream().map(customer -> customerConverter.toDto(customer)).collect(Collectors.toList());
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

	@GetMapping(value = "/{customerId}/borrows")
	public List<BorrowedDto> getCustomerBorrows(@PathVariable Long customerId)
	{
		List<Borrowed> borrowedList = customerService.getBorrows(customerId);
		return borrowedList.stream()
						   .map(borrowed -> borrowedConverter.toDto(borrowed, new CycleAvoidingMappingContext()))
						   .collect(Collectors.toList());
	}

}
