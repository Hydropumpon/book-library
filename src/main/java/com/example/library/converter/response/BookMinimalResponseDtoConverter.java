package com.example.library.converter.response;

import com.example.library.dto.response.BookMinimalResponseDto;
import com.example.library.model.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface BookMinimalResponseDtoConverter {
    BookMinimalResponseDto toDto(Book book);
}
