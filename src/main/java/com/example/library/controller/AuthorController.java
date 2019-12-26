package com.example.library.controller;


import com.example.library.converter.AuthorConverter;
import com.example.library.converter.BookConverter;
import com.example.library.converter.CycleAvoidingMappingContext;
import com.example.library.dto.AuthorDto;
import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import com.example.library.service.AuthorService;
import com.example.library.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/library/author")
public class AuthorController
{
	private AuthorService authorService;

	private AuthorConverter authorConverter;

	private BookConverter bookConverter;

	@Autowired
	public AuthorController(AuthorService authorService, AuthorConverter authorConverter, BookConverter bookConverter)
	{
		this.bookConverter = bookConverter;
		this.authorService = authorService;
		this.authorConverter = authorConverter;
	}

	@GetMapping
	@JsonView(Views.IdName.class)
	public List<AuthorDto> getAllAuthors()
	{
		return authorService.getAllAuthors().stream()
							.map(author -> authorConverter.toDto(author, new CycleAvoidingMappingContext()))
							.collect(Collectors.toList());
	}

	@JsonView(Views.FullData.class)
	@GetMapping(value = "/{id}")
	public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long id)
	{
		return new ResponseEntity<>(
				authorConverter.toDto(authorService.getOneAuthor(id), new CycleAvoidingMappingContext()),
				HttpStatus.OK);
	}

	@PostMapping
	@JsonView(Views.IdName.class)
	public ResponseEntity<AuthorDto> addAuthor(@Valid @RequestBody AuthorDto authorDto)
	{
		return new ResponseEntity<>(authorConverter.toDto(authorService.addAuthor(
				authorConverter.fromDto(authorDto, new CycleAvoidingMappingContext())),
														  new CycleAvoidingMappingContext()), HttpStatus.OK);
	}

	@JsonView(Views.IdName.class)
	@DeleteMapping(value = "/{authorId}")
	public ResponseEntity<AuthorDto> deleteAuthor(@PathVariable Long authorId)
	{
		return new ResponseEntity<>(
				authorConverter.toDto(authorService.deleteAuthor(authorId), new CycleAvoidingMappingContext()),
				HttpStatus.OK);
	}

	@JsonView(Views.FullData.class)
	@PutMapping(value = "/{authorId}")
	public AuthorDto updateAuthor(@PathVariable Long authorId, @Valid @RequestBody AuthorDto authorDto)
	{
		return authorConverter.toDto(authorService.updateAuthor(authorId, authorConverter
				.fromDto(authorDto, new CycleAvoidingMappingContext())), new CycleAvoidingMappingContext());
	}

	@JsonView(Views.IdName.class)
	@GetMapping(value = "/{authorId}/book")
	public List<BookDto> getAuthorBooks(@PathVariable Long authorId)
	{
		List<Book> books = authorService.getAuthorBooks(authorId);
		return books.stream().map(book -> bookConverter.toDto(book, new CycleAvoidingMappingContext()))
					.collect(Collectors.toList());
	}
}
