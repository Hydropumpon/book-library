package com.example.library.controller;

import com.example.library.converter.request.CustomerRequestDtoConveter;
import com.example.library.converter.response.BorrowedMinimalResponseDtoConverter;
import com.example.library.converter.response.CustomerFullResponseDtoConverter;
import com.example.library.converter.response.CustomerMinimalResponseDtoConverter;
import com.example.library.dto.request.CustomerRequestDto;
import com.example.library.dto.response.BorrowedMinimalResponseDto;
import com.example.library.dto.response.CustomerFullResponseDto;
import com.example.library.dto.response.CustomerMinimalResponseDto;
import com.example.library.model.Borrowed;
import com.example.library.model.Customer;
import com.example.library.service.CustomerService;
import com.example.library.validation.New;
import com.example.library.validation.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "library/customer")
public class CustomerController {
    private CustomerService customerService;

    private BorrowedMinimalResponseDtoConverter borrowedMinimalResponseDtoConverter;

    private CustomerRequestDtoConveter customerRequestDtoConveter;

    private CustomerFullResponseDtoConverter customerFullResponseDtoConverter;

    private CustomerMinimalResponseDtoConverter customerMinimalResponseDtoConverter;

    @Autowired
    public CustomerController(CustomerService customerService,
                              BorrowedMinimalResponseDtoConverter borrowedMinimalResponseDtoConverter,
                              CustomerRequestDtoConveter customerRequestDtoConveter,
                              CustomerFullResponseDtoConverter customerFullResponseDtoConverter,
                              CustomerMinimalResponseDtoConverter customerMinimalResponseDtoConverter) {
        this.customerService = customerService;
        this.borrowedMinimalResponseDtoConverter = borrowedMinimalResponseDtoConverter;
        this.customerRequestDtoConveter = customerRequestDtoConveter;
        this.customerFullResponseDtoConverter = customerFullResponseDtoConverter;
        this.customerMinimalResponseDtoConverter = customerMinimalResponseDtoConverter;
    }

    @GetMapping
    public List<CustomerMinimalResponseDto> getCustomers() {
        List<Customer> customers = customerService.getCustomers();
        return customers.stream().map(customer -> customerMinimalResponseDtoConverter.toDto(customer))
                        .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public CustomerFullResponseDto getCustomer(@PathVariable Long id) {
        return customerFullResponseDtoConverter.toDto(customerService.getCustomer(id));
    }

    @PostMapping
    public CustomerFullResponseDto addCustomer(
            @Validated(value = New.class) @RequestBody CustomerRequestDto customerRequestDto) {
        Customer customer = customerRequestDtoConveter.fromDto(customerRequestDto);
        return customerFullResponseDtoConverter.toDto(customerService.addCustomer(customer));
    }

    @DeleteMapping(value = "/{customerId}")
    public CustomerMinimalResponseDto deleteCustomer(@PathVariable Long customerId) {
        return customerMinimalResponseDtoConverter.toDto(customerService.deleteCustomer(customerId));
    }

    @PutMapping(value = "/{customerId}")
    public CustomerFullResponseDto updateCustomer(@PathVariable Long customerId,
                                                  @Validated(value = Update.class) @RequestBody
                                                          CustomerRequestDto customerRequestDto) {
        Customer customer = customerRequestDtoConveter.fromDto(customerRequestDto);
        return customerFullResponseDtoConverter.toDto(customerService.updateCustomer(customerId, customer));
    }

    @GetMapping(value = "/{customerId}/borrows")
    public List<BorrowedMinimalResponseDto> getCustomerBorrows(@PathVariable Long customerId) {
        List<Borrowed> borrowedList = customerService.getBorrows(customerId);
        return borrowedList.stream().map(borrowed -> borrowedMinimalResponseDtoConverter.toDto(borrowed))
                           .collect(Collectors.toList());
    }

}
