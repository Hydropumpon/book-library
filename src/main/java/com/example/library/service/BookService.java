package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.model.Book;

import javax.transaction.Transactional;
import java.util.List;

public interface BookService
{
	List<Book> getAllBooks();

	Book getOneBook(Long bookId);

	@Transactional
	Book addBook(Book book);

	@Transactional
	Book deleteBook(Long bookId);

	List<Author> getBookAuthors(Long bookId);

	@Transactional
	Book updateBook(Long bookId, Book book);
}
