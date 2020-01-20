package com.example.library.converter.request;

import com.example.library.dto.request.CustomerRequestDto;
import com.example.library.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerRequestDtoConveter {
    @Mapping(target = "borrows", ignore = true)
    Customer fromDto(CustomerRequestDto customerRequestDto);
}
