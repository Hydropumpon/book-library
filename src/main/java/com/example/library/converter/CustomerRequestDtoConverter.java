package com.example.library.converter;

import com.example.library.dto.request.CustomerRequestDto;
import com.example.library.model.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface CustomerRequestDtoConverter
{
	@Mapping(target = "borroweds", ignore = true)
	Customer fromDto(CustomerRequestDto customerRequestDto);
}
