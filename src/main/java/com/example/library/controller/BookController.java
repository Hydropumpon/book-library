package com.example.library.controller;

import com.example.library.converter.AuthorConverter;
import com.example.library.converter.BookConverter;
import com.example.library.converter.CycleAvoidingMappingContext;
import com.example.library.dto.AuthorDto;
import com.example.library.dto.BookDto;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/library/book")
public class BookController
{
	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private AuthorConverter authorConverter;

	@Autowired
	private BookConverter bookConverter;

	@GetMapping
	public List<BookDto> getAllBooks()
	{
		return bookService.getAllBooks().stream()
						  .map(book -> bookConverter.toDto(book, new CycleAvoidingMappingContext()))
						  .collect(Collectors.toList());
	}

	@GetMapping(value = "/{id}")
	public BookDto getOneBook(@PathVariable Long id)
	{
		return bookConverter.toDto(bookService.getOneBook(id), new CycleAvoidingMappingContext());
	}

	@PostMapping
	public BookDto addBook(@Valid @RequestBody BookDto bookDto)
	{
		Book book = bookConverter.fromDto(bookDto, new CycleAvoidingMappingContext());
		return bookConverter.toDto(bookService.addBook(book), new CycleAvoidingMappingContext());
	}

	@DeleteMapping(value = "/{id}")
	public BookDto deleteBook(@PathVariable Long id)
	{
		return bookConverter.toDto(bookService.deleteBook(id), new CycleAvoidingMappingContext());
	}

	@PutMapping(value = "/{bookId}")
	public BookDto updateBook(@PathVariable Long bookId, @Valid @RequestBody BookDto bookDto)
	{
		Book book = bookConverter.fromDto(bookDto, new CycleAvoidingMappingContext());
		return bookConverter.toDto(bookService.updateBook(bookId, book), new CycleAvoidingMappingContext());
	}

	@PutMapping(value = "/{bookId}/author")
	public BookDto addRelativeAuthor(@PathVariable Long bookId, @Valid @RequestBody AuthorDto authorDto)
	{
		Author author = authorConverter.fromDto(authorDto, new CycleAvoidingMappingContext());
		return bookConverter.toDto(bookService.addRelativeAuthor(bookId, author), new CycleAvoidingMappingContext());
	}

	@DeleteMapping(value = "/{bookId}/author/{authorId}")
	public BookDto deleteRelativeAuthor(@PathVariable Long bookId, @PathVariable Long authorId)
	{
		return bookConverter
				.toDto(bookService.deleteRelativeAuthor(bookId, authorId), new CycleAvoidingMappingContext());
	}

	@GetMapping(value = "/{bookId}/author")
	public List<AuthorDto> getBookAuthors(@PathVariable Long bookId)
	{
		return authorService.getBookAuthors(bookId).stream()
							.map(author -> authorConverter.toDto(author, new CycleAvoidingMappingContext()))
							.collect(Collectors.toList());
	}

}
