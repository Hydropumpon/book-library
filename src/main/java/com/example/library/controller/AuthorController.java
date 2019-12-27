package com.example.library.controller;


import com.example.library.converter.response.AuthorFullResponseDtoConverter;
import com.example.library.converter.response.AuthorMinimalResponseDtoConverter;
import com.example.library.converter.request.AuthorRequestDtoConverter;
import com.example.library.converter.response.BookMinimalResponseDtoConverter;
import com.example.library.dto.request.AuthorRequestDto;
import com.example.library.dto.response.AuthorFullResponseDto;
import com.example.library.dto.response.AuthorMinimalResponseDto;
import com.example.library.dto.response.BookMinimalResponseDto;
import com.example.library.model.Book;
import com.example.library.service.AuthorService;
import com.example.library.views.transfer.New;
import com.example.library.views.transfer.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/library/author")
public class AuthorController
{
	private AuthorService authorService;

	private AuthorRequestDtoConverter authorRequestDtoConverter;

	private AuthorMinimalResponseDtoConverter authorMinimalResponseDtoConverter;

	private AuthorFullResponseDtoConverter authorFullResponseDtoConverter;

	private BookMinimalResponseDtoConverter bookMinimalResponseConverter;

	@Autowired
	public AuthorController(AuthorService authorService, AuthorRequestDtoConverter authorRequestDtoConverter,
							AuthorMinimalResponseDtoConverter authorMinimalResponseDtoConverter,
							AuthorFullResponseDtoConverter authorFullResponseDtoConverter,
							BookMinimalResponseDtoConverter bookMinimalResponseConverter)
	{
		this.bookMinimalResponseConverter = bookMinimalResponseConverter;
		this.authorService = authorService;
		this.authorRequestDtoConverter = authorRequestDtoConverter;
		this.authorMinimalResponseDtoConverter = authorMinimalResponseDtoConverter;
		this.authorFullResponseDtoConverter = authorFullResponseDtoConverter;
	}

	@GetMapping
	public List<AuthorMinimalResponseDto> getAllAuthors()
	{
		return authorService.getAllAuthors().stream().map(author -> authorMinimalResponseDtoConverter.toDto(author))
							.collect(Collectors.toList());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<AuthorFullResponseDto> getAuthor(@PathVariable Long id)
	{
		return new ResponseEntity<>(authorFullResponseDtoConverter.toDto(authorService.getOneAuthor(id)), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<AuthorMinimalResponseDto> addAuthor(
			@Validated(New.class) @RequestBody AuthorRequestDto authorDto)
	{
		return new ResponseEntity<>(authorMinimalResponseDtoConverter.toDto(authorService.addAuthor(
				authorRequestDtoConverter.fromDto(authorDto))), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{authorId}")
	public ResponseEntity<AuthorMinimalResponseDto> deleteAuthor(@PathVariable Long authorId)
	{
		return new ResponseEntity<>(authorMinimalResponseDtoConverter.toDto(authorService.deleteAuthor(authorId)),
									HttpStatus.OK);
	}

	@PutMapping(value = "/{authorId}")
	public AuthorMinimalResponseDto updateAuthor(@PathVariable Long authorId,
												 @Validated(Update.class) @RequestBody AuthorRequestDto authorDto)
	{
		return authorMinimalResponseDtoConverter
				.toDto(authorService.updateAuthor(authorId, authorRequestDtoConverter.fromDto(authorDto)));
	}

	@GetMapping(value = "/{authorId}/book")
	public List<BookMinimalResponseDto> getAuthorBooks(@PathVariable Long authorId)
	{
		List<Book> books = authorService.getAuthorBooks(authorId);
		return books.stream().map(book -> bookMinimalResponseConverter.toDto(book)).collect(Collectors.toList());
	}
}
