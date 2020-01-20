package com.example.library.service;

import com.example.library.model.Borrowed;
import com.example.library.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();

    Customer getCustomer(Long id);

    Customer addCustomer(Customer customer);

    Customer deleteCustomer(Long customerId);

    Customer updateCustomer(Long customerId, Customer customer);

    List<Borrowed> getBorrows(Long customerId);
}
