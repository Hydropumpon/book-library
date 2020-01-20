package com.example.library.converter.response;

import com.example.library.dto.response.BookMinimalResponseDto;
import com.example.library.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMinimalResponseDtoConverter {
    BookMinimalResponseDto toDto(Book book);
}
