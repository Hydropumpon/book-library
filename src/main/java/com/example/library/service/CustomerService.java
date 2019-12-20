package com.example.library.service;

import com.example.library.common.exception.ConflictException;
import com.example.library.common.exception.NotFoundException;
import com.example.library.common.exception.ServiceErrorCode;
import com.example.library.dto.CustomerDto;
import com.example.library.model.Customer;
import com.example.library.repository.CustomerRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService
{
	public final String CUSTOMER_NOT_FOUND = "Customer not found";
	public final String CUSTOMER_EMAIL_ALREADY_EXIST = "Customer with same email already exists";
	public final String CUSTOMER_LOGIN_ALREADY_EXIST = "Customer with same login already exists";

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> getCustomers()
	{
		return customerRepository.findAll();
	}

	public Customer getCustomer(Long id)
	{
		return customerRepository.findById(id).orElseThrow(
				() -> new NotFoundException(CUSTOMER_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
	}

	public Customer addCustomer(Customer customer)
	{
		if (customerRepository.existsByEmail(customer.getEmail()))
		{
			throw new ConflictException(CUSTOMER_EMAIL_ALREADY_EXIST, ServiceErrorCode.ALREADY_EXIST);
		}
		if (customerRepository.existsByLogin(customer.getLogin()))
		{
			throw new ConflictException(CUSTOMER_LOGIN_ALREADY_EXIST, ServiceErrorCode.ALREADY_EXIST);
		}
		return customerRepository.save(customer);
	}

	public Customer deleteCustomer(Long customerId)
	{
		Customer customer = customerRepository.findById(customerId).orElseThrow(
				() -> new NotFoundException(CUSTOMER_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
		customerRepository.delete(customer);
		return customer;
	}

	public Customer updateCustomer(Long customerId, Customer customer)
	{
		Customer customerDb = customerRepository.findById(customerId).orElseThrow(
				() -> new NotFoundException(CUSTOMER_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
		Optional<Customer> customerWithSameLogin = customerRepository.findByLogin(customer.getLogin());
		Optional<Customer> customerWithSameEmail = customerRepository.findByEmail(customer.getEmail());

		if (customerWithSameLogin.isPresent() && (!customerDb.getId().equals(customerWithSameLogin.get().getId())))
		{
			throw new ConflictException(CUSTOMER_LOGIN_ALREADY_EXIST, ServiceErrorCode.ALREADY_EXIST);
		}
		if (customerWithSameEmail.isPresent() && (!customerDb.getId().equals(customerWithSameEmail.get().getId())))
		{
			throw new ConflictException(CUSTOMER_EMAIL_ALREADY_EXIST, ServiceErrorCode.ALREADY_EXIST);
		}
		customer.setId(customerDb.getId());
		return customerRepository.save(customer);
	}
}
