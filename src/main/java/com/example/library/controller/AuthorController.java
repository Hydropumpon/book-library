package com.example.library.controller;


import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/library/author")
public class AuthorController
{
	@Autowired
	AuthorService authorService;

	@Autowired
	BookService bookService;

	@GetMapping
	public List<Author> getAllAuthors()
	{
		return authorService.getAllAuthors();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Author> getAuthor(@PathVariable Long id)
	{
		return new ResponseEntity<>(authorService.getOneAuthor(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Author> addAuthor(@Valid @RequestBody Author author)
	{
		return new ResponseEntity<>(authorService.addAuthor(author), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{authorId}")
	public ResponseEntity<Author> deleteAuthor(@PathVariable Long authorId)
	{
		return new ResponseEntity<>(authorService.deleteAuthor(authorId), HttpStatus.OK);
	}

	@PutMapping(value = "/{authorId}")
	public Author updateAuthor(@PathVariable Long authorId, @Valid @RequestBody Author author)
	{
		return authorService.updateAuthor(authorId, author);
	}

	@PutMapping(value = "/{authorId}/book")
	public ResponseEntity<Author> addRelativeBook(@PathVariable Long authorId, @Valid @RequestBody Book book)
	{
		return new ResponseEntity<>(authorService.addRelativeBook(authorId, book), HttpStatus.OK);
	}

	@GetMapping(value = "/{authorId}/book")
	public List<Book> getBooksByAuthor(@PathVariable Long authorId)
	{
		return bookService.findAllByAuthor(authorId);
	}

	@DeleteMapping(value = "/{authorId}/book/{bookId}")
	public Author deleteRelativeBook(@PathVariable Long authorId, @PathVariable Long bookId)
	{
		return authorService.deleteRelativeBook(authorId, bookId);
	}

}
