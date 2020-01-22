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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomer(Long id) {
        return getCustomerById(id);
    }

    @Override
    @Transactional
    public Customer addCustomer(Customer customer) {
        if (customerRepository.existsByLoginOrEmail(customer.getLogin(), customer.getEmail())) {
            throw new ConflictException(ErrorMessage.CUSTOMER_ALREADY_EXIST, ServiceErrorCode.ALREADY_EXIST);
        }
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public Customer deleteCustomer(Long customerId) {
        Customer customer = getCustomerById(customerId);
        customerRepository.delete(customer);
        return customer;
    }

    @Override
    @Transactional
    public Customer updateCustomer(Long customerId, Customer customer) {
        Customer customerDb = getCustomerById(customerId);
        checkCustomerDuplicate(customer, customerDb);
        return customerRepository.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Borrowed> getBorrows(Long customerId) {
        Customer customerDb = getCustomerById(customerId);
        return new ArrayList<>(customerDb.getBorrows());
    }

    private Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
    }

    private void checkCustomerDuplicate(Customer customer, Customer customerDb) {

        customerRepository.findByLogin(customer.getLogin())
                          .filter(customer1 -> customer1.getLogin().equals(customerDb.getLogin())).orElseThrow(
                () -> new ConflictException(ErrorMessage.CUSTOMER_LOGIN_ALREADY_EXIST, ServiceErrorCode.ALREADY_EXIST));

        customerRepository.findByEmail(customer.getEmail())
                          .filter(customer1 -> customer1.getEmail().equals(customerDb.getEmail())).orElseThrow(
                () -> new ConflictException(ErrorMessage.CUSTOMER_EMAIL_ALREADY_EXIST, ServiceErrorCode.ALREADY_EXIST));
    }
}
