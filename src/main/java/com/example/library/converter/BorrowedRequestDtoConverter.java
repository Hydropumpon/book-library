package com.example.library.converter;

import com.example.library.dto.request.BookRequestDto;
import com.example.library.dto.request.BorrowedRequestDto;
import com.example.library.model.Book;
import com.example.library.model.Borrowed;
import com.example.library.model.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface BorrowedRequestDtoConverter
{
	@Mapping(target = "returnTillDate", ignore = true)
	@Mapping(target = "returnDate", ignore = true)
	@Mapping(target = "borrowDate", ignore = true)
	Borrowed fromDto(BorrowedRequestDto borrowedRequestDto);

	default Book mapBook(Long id)
	{
		return new Book(id);
	}

	default Customer mapCustomer(Long id)
	{
		return new Customer(id);
	}
}
