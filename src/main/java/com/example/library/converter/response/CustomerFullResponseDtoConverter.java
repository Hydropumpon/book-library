package com.example.library.converter.response;

import com.example.library.dto.response.CustomerFullResponseDto;
import com.example.library.model.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, uses =
		{BorrowedMinimalResponseDtoConverter.class})
public interface CustomerFullResponseDtoConverter
{
	CustomerFullResponseDto toDto(Customer customer);
}
