package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/library/book")
public class BookController
{
	@Autowired
	BookService bookService;

	@Autowired
	AuthorService authorService;

	@GetMapping
	public List<Book> getAllBooks()
	{
		return bookService.getAllBooks();
	}

	@GetMapping(value = "/{id}")
	public Book getOneBook(@PathVariable Long id)
	{
		return bookService.getOneBook(id);
	}

	@PostMapping
	public Book addBook(@Valid @RequestBody Book book)
	{
		return bookService.addBook(book);
	}

	@DeleteMapping(value = "/{id}")
	public Book deleteBook(@PathVariable Long id)
	{
		return bookService.deleteBook(id);
	}

	@PutMapping(value = "/{bookId}")
	public Book updateBook(@PathVariable Long bookId, @Valid @RequestBody Book book)
	{
		return bookService.updateBook(bookId, book);
	}

	@PutMapping(value = "/{bookId}/author")
	public Book addRelativeAuthor(@PathVariable Long bookId, @Valid @RequestBody Author author)
	{
		return bookService.addRelativeAuthor(bookId, author);
	}

	@DeleteMapping(value = "/{bookId}/author/{authorId}")
	public Book deleteRelativeAuthor(@PathVariable Long bookId, @PathVariable Long authorId)
	{
		return bookService.deleteRelativeAuthor(bookId, authorId);
	}

	@GetMapping(value = "/{bookId}/author")
	public List<Author> getBookAuthors(@PathVariable Long bookId)
	{
		return authorService.getBookAuthors(bookId);
	}

}
