package com.example.library.converter.response;

import com.example.library.dto.response.CustomerMinimalResponseDto;
import com.example.library.model.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface CustomerMinimalResponseDtoConverter {
    CustomerMinimalResponseDto toDto(Customer customer);
}
