package com.example.library.controller;


import com.example.library.converter.request.AuthorRequestDtoConverter;
import com.example.library.converter.response.AuthorFullResponseDtoConverter;
import com.example.library.converter.response.AuthorMinimalResponseDtoConverter;
import com.example.library.converter.response.BookMinimalResponseDtoConverter;
import com.example.library.dto.request.AuthorRequestDto;
import com.example.library.dto.response.AuthorFullResponseDto;
import com.example.library.dto.response.AuthorMinimalResponseDto;
import com.example.library.dto.response.BookMinimalResponseDto;
import com.example.library.model.Book;
import com.example.library.service.AuthorService;
import com.example.library.validation.New;
import com.example.library.validation.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/library/author")
public class AuthorController {
    private AuthorService authorService;

    private AuthorRequestDtoConverter authorRequestDtoConverter;

    private AuthorMinimalResponseDtoConverter authorMinimalResponseDtoConverter;

    private AuthorFullResponseDtoConverter authorFullResponseDtoConverter;

    private BookMinimalResponseDtoConverter bookMinimalResponseConverter;

    @Autowired
    public AuthorController(AuthorService authorService, AuthorRequestDtoConverter authorRequestDtoConverter,
                            AuthorMinimalResponseDtoConverter authorMinimalResponseDtoConverter,
                            AuthorFullResponseDtoConverter authorFullResponseDtoConverter,
                            BookMinimalResponseDtoConverter bookMinimalResponseConverter) {
        this.bookMinimalResponseConverter = bookMinimalResponseConverter;
        this.authorService = authorService;
        this.authorRequestDtoConverter = authorRequestDtoConverter;
        this.authorMinimalResponseDtoConverter = authorMinimalResponseDtoConverter;
        this.authorFullResponseDtoConverter = authorFullResponseDtoConverter;
    }

    @GetMapping
    public List<AuthorMinimalResponseDto> getAllAuthors() {
        return authorService.getAllAuthors().stream().map(author -> authorMinimalResponseDtoConverter.toDto(author))
                            .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public AuthorFullResponseDto getAuthor(@PathVariable Long id) {
        return authorFullResponseDtoConverter.toDto(authorService.getOneAuthor(id));
    }

    @PostMapping
    public AuthorMinimalResponseDto addAuthor(
            @Validated(New.class) @RequestBody AuthorRequestDto authorDto) {
        return authorMinimalResponseDtoConverter
                .toDto(authorService.addAuthor(authorRequestDtoConverter.fromDto(authorDto)));
    }

    @DeleteMapping(value = "/{authorId}")
    public AuthorMinimalResponseDto deleteAuthor(@PathVariable Long authorId) {
        return authorMinimalResponseDtoConverter.toDto(authorService.deleteAuthor(authorId));
    }

    @PutMapping(value = "/{authorId}")
    public AuthorMinimalResponseDto updateAuthor(@PathVariable Long authorId,
                                                 @Validated(Update.class) @RequestBody AuthorRequestDto authorDto) {
        return authorMinimalResponseDtoConverter
                .toDto(authorService.updateAuthor(authorId, authorRequestDtoConverter.fromDto(authorDto)));
    }

    @GetMapping(value = "/pageable")
    public List<AuthorMinimalResponseDto> getPages(Pageable pageable) {
        return authorService.getAuthorPages(pageable)
                            .getContent()
                            .stream()
                            .map(author -> authorMinimalResponseDtoConverter.toDto(author))
                            .collect(Collectors.toList());
    }


    @GetMapping(value = "/{authorId}/book")
    public List<BookMinimalResponseDto> getAuthorBooks(@PathVariable Long authorId) {
        List<Book> books = authorService.getAuthorBooks(authorId);
        return books.stream().map(book -> bookMinimalResponseConverter.toDto(book)).collect(Collectors.toList());
    }
}
