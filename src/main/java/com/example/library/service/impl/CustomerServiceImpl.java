package com.example.library.service.impl;

import com.example.library.common.exception.ConflictException;
import com.example.library.common.exception.ErrorMessage;
import com.example.library.common.exception.NotFoundException;
import com.example.library.common.exception.ServiceErrorCode;
import com.example.library.model.Borrowed;
import com.example.library.model.Customer;
import com.example.library.repository.CustomerRepository;
import com.example.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService
{
	private CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository)
	{
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> getCustomers()
	{
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomer(Long id)
	{
		return getCustomerById(id);
	}

	@Override
	@Transactional
	public Customer addCustomer(Customer customer)
	{
		checkCustomerExistByEmail(customer);
		checkCustomerExistByLogin(customer);
		return customerRepository.save(customer);
	}

	@Override
	@Transactional
	public Customer deleteCustomer(Long customerId)
	{
		Customer customer = getCustomerById(customerId);
		customerRepository.delete(customer);
		return customer;
	}

	@Override
	@Transactional
	public Customer updateCustomer(Long customerId, Customer customer)
	{
		Customer customerDb = getCustomerById(customerId);
		checkCustomerDuplicate(customer, customerDb);
		return customerRepository.save(customer);
	}

	private void checkCustomerDuplicate(Customer customer, Customer customerDb)
	{
		Optional<Customer> customerWithSameLogin = customerRepository.findByLogin(customer.getLogin());
		Optional<Customer> customerWithSameEmail = customerRepository.findByEmail(customer.getEmail());

		if (customerWithSameLogin.isPresent() && !(customerDb.getId().equals(customerWithSameLogin.get().getId())))
		{
			throw new ConflictException(ErrorMessage.CUSTOMER_LOGIN_ALREADY_EXIST, ServiceErrorCode.ALREADY_EXIST);
		}
		if (customerWithSameEmail.isPresent() && !(customerDb.getId().equals(customerWithSameEmail.get().getId())))
		{
			throw new ConflictException(ErrorMessage.CUSTOMER_EMAIL_ALREADY_EXIST, ServiceErrorCode.ALREADY_EXIST);
		}
	}

	@Override
	public List<Borrowed> getBorrows(Long customerId)
	{
		Customer customerDb = getCustomerById(customerId);
		return new ArrayList<>(customerDb.getBorrowedSet());
	}

	private Customer getCustomerById(Long customerId)
	{
		return customerRepository.findById(customerId).orElseThrow(
				() -> new NotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
	}

	private void checkCustomerExistByLogin(Customer customer)
	{
		if (customerRepository.existsByLogin(customer.getLogin()))
		{
			throw new ConflictException(ErrorMessage.CUSTOMER_LOGIN_ALREADY_EXIST, ServiceErrorCode.ALREADY_EXIST);
		}
	}

	private void checkCustomerExistByEmail(Customer customer)
	{
		if (customerRepository.existsByEmail(customer.getEmail()))
		{
			throw new ConflictException(ErrorMessage.CUSTOMER_EMAIL_ALREADY_EXIST, ServiceErrorCode.ALREADY_EXIST);
		}
	}
}
