package com.example.library.converter.response;

import com.example.library.dto.response.BookFullResponseDto;
import com.example.library.model.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, uses =
        {AuthorMinimalResponseDtoConverter.class})
public interface BookFullResponseDtoConverter {
    BookFullResponseDto toDto(Book book);
}
