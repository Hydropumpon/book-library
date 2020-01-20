package com.example.library.converter.request;

import com.example.library.dto.request.CustomerRequestDto;
import com.example.library.model.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerRequestDtoConveter {
    @Mapping(target = "borrows", ignore = true)
    Customer fromDto(CustomerRequestDto customerRequestDto);
}
