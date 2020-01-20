package com.example.library.converter.response;

import com.example.library.dto.response.AuthorFullResponseDto;
import com.example.library.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {BookMinimalResponseDtoConverter.class})
public interface AuthorFullResponseDtoConverter {
    AuthorFullResponseDto toDto(Author author);
}
