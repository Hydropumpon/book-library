package com.example.library.converter.request;

import com.example.library.configuration.LibraryProperties;
import com.example.library.dto.request.BorrowedRequestDto;
import com.example.library.model.Book;
import com.example.library.model.Borrowed;
import com.example.library.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class BorrowedRequestDtoConverter {
    @Autowired
    LibraryProperties libraryProperties;

    @Mapping(target = "returnTillDate", expression = "java(java.time.LocalDate.now().plusDays(libraryProperties" +
            ".getDays" + "()))")
    @Mapping(target = "returnDate", ignore = true)
    @Mapping(target = "borrowDate", expression = "java(java.time.LocalDate.now())")
    public abstract Borrowed fromDto(BorrowedRequestDto borrowedRequestDto);

    Book mapBook(Long id) {
        return new Book(id);
    }

    Customer mapCustomer(Long id) {
        return new Customer(id);
    }
}
