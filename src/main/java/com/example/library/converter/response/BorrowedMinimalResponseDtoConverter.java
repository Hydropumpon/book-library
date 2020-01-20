package com.example.library.converter.response;

import com.example.library.dto.response.BorrowedMinimalResponseDto;
import com.example.library.model.Book;
import com.example.library.model.Borrowed;
import com.example.library.model.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface BorrowedMinimalResponseDtoConverter {
    BorrowedMinimalResponseDto toDto(Borrowed borrowed);

    default String mapCustomer(Customer customer) {
        return customer.getLogin();
    }

    default String mapBook(Book book) {
        return book.getTitle();
    }
}
