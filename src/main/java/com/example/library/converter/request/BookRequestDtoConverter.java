package com.example.library.converter.request;

import com.example.library.dto.request.BookRequestDto;
import com.example.library.model.Author;
import com.example.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookRequestDtoConverter {
    @Mapping(target = "borrowedSet", ignore = true)
    Book fromDto(BookRequestDto bookRequestDto);

    default Set<Author> mapAuthor(List<Long> authorsId) {
        return authorsId.stream().map(Author::new).collect(Collectors.toSet());
    }
}
