package com.example.library.converter;

import com.example.library.dto.CustomerDto;
import com.example.library.model.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface CustomerConverter extends BasicConverter<Customer, CustomerDto>
{
}
