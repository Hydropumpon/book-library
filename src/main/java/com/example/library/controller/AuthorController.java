package com.example.library.controller;


import com.example.library.converter.AuthorConverter;
import com.example.library.converter.BookConverter;
import com.example.library.converter.CycleAvoidingMappingContext;
import com.example.library.dto.AuthorDto;
import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
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
	@Autowired
	private AuthorService authorService;

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorConverter authorConverter;

	@Autowired
	private BookConverter bookConverter;

	@GetMapping
	public List<AuthorDto> getAllAuthors()
	{
		return authorService.getAllAuthors().stream().map(author -> authorConverter.toDto(author, new CycleAvoidingMappingContext())).collect(
				Collectors.toList());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long id)
	{
		return new ResponseEntity<>(
				authorConverter.toDto(authorService.getOneAuthor(id), new CycleAvoidingMappingContext()),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<AuthorDto> addAuthor(@Valid @RequestBody AuthorDto authorDto)
	{
		return new ResponseEntity<>(authorConverter.toDto(authorService.addAuthor(
				authorConverter.fromDto(authorDto, new CycleAvoidingMappingContext())),
														  new CycleAvoidingMappingContext()), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{authorId}")
	public ResponseEntity<AuthorDto> deleteAuthor(@PathVariable Long authorId)
	{
		return new ResponseEntity<>(
				authorConverter.toDto(authorService.deleteAuthor(authorId), new CycleAvoidingMappingContext()),
				HttpStatus.OK);
	}

	@PutMapping(value = "/{authorId}")
	public AuthorDto updateAuthor(@PathVariable Long authorId, @Valid @RequestBody AuthorDto authorDto)
	{
		return authorConverter.toDto(authorService.updateAuthor(authorId, authorConverter
				.fromDto(authorDto, new CycleAvoidingMappingContext())), new CycleAvoidingMappingContext());


	}

	@PutMapping(value = "/{authorId}/book")
	public ResponseEntity<AuthorDto> addRelativeBook(@PathVariable Long authorId, @Valid @RequestBody BookDto bookDto)
	{
		Book book = bookConverter.fromDto(bookDto, new CycleAvoidingMappingContext());

		return new ResponseEntity<>(authorConverter.toDto(authorService.addRelativeBook(authorId, book), new CycleAvoidingMappingContext()), HttpStatus.OK);
	}

	@GetMapping(value = "/{authorId}/book")
	public List<BookDto> getBooksByAuthor(@PathVariable Long authorId)
	{
		return bookService.findAllByAuthor(authorId).stream().map(book -> bookConverter.toDto(book, new CycleAvoidingMappingContext())).collect(Collectors.toList());
	}

	@DeleteMapping(value = "/{authorId}/book/{bookId}")
	public AuthorDto deleteRelativeBook(@PathVariable Long authorId, @PathVariable Long bookId)
	{
		return authorConverter.toDto(authorService.deleteRelativeBook(authorId, bookId), new CycleAvoidingMappingContext());
	}

}
