package com.example.library.converter;

import com.example.library.dto.CustomerDto;
import com.example.library.model.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface CustomerConverter
{
	@Mapping(target = "borrowedSet", ignore = true)
	Customer fromDto(CustomerDto dto);

	CustomerDto toDto(Customer entity);
}
