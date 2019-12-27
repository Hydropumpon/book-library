package com.example.library.controller;

import com.example.library.converter.AuthorMinimalResponseConverter;
import com.example.library.converter.BookFullResponseConverter;
import com.example.library.converter.BookMinimalResponseConverter;
import com.example.library.converter.BookRequestDtoConverter;
import com.example.library.dto.request.BookRequestDto;
import com.example.library.dto.response.AuthorMinimalResponseDto;
import com.example.library.dto.response.BookFullResponseDto;
import com.example.library.dto.response.BookMinimalResponseDto;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import com.example.library.views.transfer.New;
import com.example.library.views.transfer.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/library/book")
public class BookController
{
	private BookService bookService;


	private BookRequestDtoConverter bookRequestDtoConverter;

	private BookMinimalResponseConverter bookMinimalResponseConverter;

	private BookFullResponseConverter bookFullResponseConverter;

	private AuthorMinimalResponseConverter authorMinimalResponseConverter;

	@Autowired
	public BookController(BookService bookService, BookRequestDtoConverter bookRequestDtoConverter,
						  BookMinimalResponseConverter bookMinimalResponseConverter,
						  BookFullResponseConverter bookFullResponseConverter,
						  AuthorMinimalResponseConverter authorMinimalResponseConverter)
	{
		this.bookService = bookService;
		this.bookRequestDtoConverter = bookRequestDtoConverter;
		this.bookMinimalResponseConverter = bookMinimalResponseConverter;
		this.bookFullResponseConverter = bookFullResponseConverter;
		this.authorMinimalResponseConverter = authorMinimalResponseConverter;
	}

	@GetMapping
	public List<BookMinimalResponseDto> getAllBooks()
	{
		return bookService.getAllBooks().stream().map(book -> bookMinimalResponseConverter.toDto(book))
						  .collect(Collectors.toList());
	}

	@GetMapping(value = "/{id}")
	public BookFullResponseDto getOneBook(@PathVariable Long id)
	{
		return bookFullResponseConverter.toDto(bookService.getOneBook(id));
	}

	@PostMapping
	public BookMinimalResponseDto addBook(@Validated(New.class) @RequestBody BookRequestDto bookRequestDto)
	{
		Book book = bookRequestDtoConverter.fromDto(bookRequestDto);
		return bookMinimalResponseConverter.toDto(bookService.addBook(book));
	}

	@DeleteMapping(value = "/{id}")
	public BookMinimalResponseDto deleteBook(@PathVariable Long id)
	{
		return bookMinimalResponseConverter.toDto(bookService.deleteBook(id));
	}

	@PutMapping(value = "/{bookId}")
	public BookFullResponseDto updateBook(@PathVariable Long bookId,
										  @Validated(Update.class) @RequestBody BookRequestDto bookRequestDto)
	{
		Book book = bookRequestDtoConverter.fromDto(bookRequestDto);
		return bookFullResponseConverter.toDto(bookService.updateBook(bookId, book));
	}

	@GetMapping(value = "/{bookId}/author")
	public List<AuthorMinimalResponseDto> getBookAuthors(@PathVariable Long bookId)
	{
		List<Author> authors = bookService.getBookAuthors(bookId);
		return authors.stream().map(author -> authorMinimalResponseConverter.toDto(author))
					  .collect(Collectors.toList());
	}

}
