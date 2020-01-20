package com.example.library.converter.response;

import com.example.library.dto.response.BorrowedFullResponseDto;
import com.example.library.model.Borrowed;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, uses =
        {CustomerMinimalResponseDtoConverter.class, BookMinimalResponseDtoConverter.class})
public interface BorrowedFullResponseDtoConverter {
    BorrowedFullResponseDto toDto(Borrowed borrowed);
}
