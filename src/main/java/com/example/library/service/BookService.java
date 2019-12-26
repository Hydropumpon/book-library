package com.example.library.service;

import com.example.library.common.exception.ConflictException;
import com.example.library.common.exception.ErrorMessage;
import com.example.library.common.exception.NotFoundException;
import com.example.library.common.exception.ServiceErrorCode;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService
{
	private BookRepository bookRepository;

	@Autowired
	public BookService(BookRepository bookRepository)
	{
		this.bookRepository = bookRepository;
	}

	public List<Book> getAllBooks()
	{
		return bookRepository.findAll();
	}

	public Book getOneBook(Long bookId)
	{
		return bookRepository.findById(bookId).orElseThrow(
				() -> new NotFoundException(ErrorMessage.BOOK_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
	}

	@Transactional
	public Book addBook(Book book)
	{
		checkBookExistByTitle(book);
		book.getAuthors().forEach(author -> author.addBook(book));
		return bookRepository.save(book);
	}

	@Transactional
	public Book deleteBook(Long bookId)
	{
		Book bookDb = getBookById(bookId);
		bookDb.getAuthors().forEach(author -> author.removeBook(bookDb));
		bookRepository.delete(bookDb);
		return bookDb;
	}

	public List<Author> getBookAuthors(Long bookId)
	{
		Book bookDb = getBookById(bookId);
		return new ArrayList<>(bookDb.getAuthors());
	}

	public Book updateBook(Long bookId, Book book)
	{
		Book bookDb = getBookById(bookId);
		checkTitleDuplicate(book, bookDb);
		return bookRepository.save(book);
	}

	private Book getBookById(Long bookId)
	{
		return bookRepository.findById(bookId).orElseThrow(
				() -> new NotFoundException(ErrorMessage.BOOK_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
	}

	private void checkBookExistByTitle(Book book)
	{
		Optional<Book> bookDb = bookRepository.findByTitle(book.getTitle());
		if (bookDb.isPresent())
		{
			throw new ConflictException(ErrorMessage.BOOK_EXIST, ServiceErrorCode.ALREADY_EXIST);
		}
	}

	private void checkTitleDuplicate(Book book, Book bookDb)
	{
		Optional<Book> bookWithSameTitle = bookRepository.findByTitle(book.getTitle());
		if ((bookWithSameTitle.isPresent()) && (!bookDb.getId().equals(bookWithSameTitle.get().getId())))
		{
			throw new ConflictException(ErrorMessage.BOOK_EXIST, ServiceErrorCode.ALREADY_EXIST);
		}
	}

}
