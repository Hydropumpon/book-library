package com.example.library.service;

import com.example.library.common.exception.ConflictException;
import com.example.library.common.exception.NotFoundException;
import com.example.library.common.exception.ServiceErrorCode;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService
{
	static final String BOOK_NOT_FOUND = "Book not found";
	static final String BOOK_ALREADY_EXIST = "Book already exist";

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	public List<Book> getAllBooks()
	{
		return bookRepository.findAll();
	}

	public Book getOneBook(Long bookId)
	{
		return bookRepository.findById(bookId)
							 .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
	}

	public Book addBook(Book book)
	{
		return bookRepository.save(book);
	}

	public Book deleteBook(Long bookId)
	{
		Book bookDb = bookRepository.findById(bookId).orElseThrow(
				() -> new NotFoundException(BOOK_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
		bookDb.getAuthors().forEach(author -> author.getBooks().remove(bookDb));
		bookRepository.delete(bookDb);
		return bookDb;
	}


	public Book addRelativeAuthor(Long bookId, Author author)
	{
		Book bookDb = bookRepository.findById(bookId).orElseThrow(
				() -> new NotFoundException(BOOK_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
		Author authorDb = authorRepository.findById(author.getId()).orElseThrow(
				() -> new NotFoundException(AuthorService.AUTHOR_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
		bookDb.getAuthors().add(authorDb);
		authorDb.getBooks().add(bookDb);
		return bookRepository.save(bookDb);
	}

	public List<Book> findAllByAuthor(Long authorId)
	{
		Author authorDb = authorRepository.findById(authorId).orElseThrow(
				() -> new NotFoundException(AuthorService.AUTHOR_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
		return bookRepository.findAllByAuthors(authorDb);
	}

	public Book updateBook(Long bookId, Book book)
	{
		Book bookDb = bookRepository.findById(bookId).orElseThrow(
				() -> new NotFoundException(BOOK_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
		Optional<Book> bookWithSameName = bookRepository.findByTitle(book.getTitle());
		if (bookWithSameName.isPresent() && (!bookDb.getId().equals(bookWithSameName.get().getId())))
		{
			throw new ConflictException(BOOK_ALREADY_EXIST, ServiceErrorCode.ALREADY_EXIST);
		}
		book.setId(bookDb.getId());
		book.setAuthors(bookDb.getAuthors());
		return bookRepository.save(book);
	}

	public Book deleteRelativeAuthor(Long bookId, Long authorId)
	{
		Book bookDb = bookRepository.findById(bookId).orElseThrow(
				() -> new NotFoundException(BOOK_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
		Author authorDb = authorRepository.findById(authorId).orElseThrow(
				() -> new NotFoundException(AuthorService.AUTHOR_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
		if (!bookDb.getAuthors().contains(authorDb))
		{
			throw new NotFoundException(AuthorService.AUTHOR_NOT_FOUND, ServiceErrorCode.NOT_FOUND);
		}
		bookDb.getAuthors().remove(authorDb);
		authorDb.getBooks().remove(bookDb);
		return bookRepository.save(bookDb);
	}
}
