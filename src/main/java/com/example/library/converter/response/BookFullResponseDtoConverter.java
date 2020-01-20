package com.example.library.converter.response;

import com.example.library.dto.response.BookFullResponseDto;
import com.example.library.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses =
        {AuthorMinimalResponseDtoConverter.class})
public interface BookFullResponseDtoConverter {
    BookFullResponseDto toDto(Book book);
}
