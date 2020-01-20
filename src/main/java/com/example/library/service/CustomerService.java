package com.example.library.service;

import com.example.library.model.Borrowed;
import com.example.library.model.Customer;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();

    Customer getCustomer(Long id);

    @Transactional
    Customer addCustomer(Customer customer);

    @Transactional
    Customer deleteCustomer(Long customerId);

    @Transactional
    Customer updateCustomer(Long customerId, Customer customer);

    List<Borrowed> getBorrows(Long customerId);
}
