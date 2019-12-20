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
public class AuthorService
{
	static final String AUTHOR_EXIST = "Author already exist";
	static final String AUTHOR_NOT_FOUND = "Author not found";

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;

	public List<Author> getAllAuthors()
	{
		return authorRepository.findAll();
	}

	public Author getOneAuthor(Long authorId)
	{
		return authorRepository.findById(authorId)
							   .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
	}

	public Author addAuthor(Author author)
	{
		Optional<Author> authorDb = authorRepository.findByName(author.getName());
		if (authorDb.isPresent())
		{
			throw new ConflictException(AUTHOR_EXIST, ServiceErrorCode.ALREADY_EXIST);
		}
		return authorRepository.save(author);
	}

	public Author deleteAuthor(Long authorId)
	{
		Author authorDb = authorRepository.findById(authorId).orElseThrow(
				() -> new NotFoundException(AUTHOR_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
		authorDb.getBooks().forEach(book -> book.getAuthors().remove(authorDb));
		authorRepository.delete(authorDb);
		return authorDb;
	}

	public Author addRelativeBook(Long authorId, Book book)
	{
		Author authorDb = authorRepository.findById(authorId).orElseThrow(
				() -> new NotFoundException(AUTHOR_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
		Book bookDb = bookRepository.findById(book.getId()).orElseThrow(
				() -> new NotFoundException(BookService.BOOK_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
		authorDb.getBooks().add(bookDb);
		bookDb.getAuthors().add(authorDb);
		return authorRepository.save(authorDb);
	}

	public List<Author> getBookAuthors(Long bookId)
	{
		Book bookDb = bookRepository.findById(bookId).orElseThrow(
				() -> new NotFoundException(BookService.BOOK_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
		return authorRepository.findAllByBooks(bookDb);
	}

	public Author updateAuthor(Long authorId, Author author)
	{
		Author authorDb = authorRepository.findById(authorId).orElseThrow(
				() -> new NotFoundException(AUTHOR_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
		Optional<Author> authorWithSameName = authorRepository.findByName(author.getName());
		if (authorWithSameName.isPresent() && (!authorDb.getId().equals(authorWithSameName.get().getId())))
		{
			throw new ConflictException(AUTHOR_EXIST, ServiceErrorCode.ALREADY_EXIST);
		}
		author.setId(authorDb.getId());
		author.setBooks(authorDb.getBooks());
		return authorRepository.save(author);
	}

	public Author deleteRelativeBook(Long authorId, Long bookId)
	{
		Author authorDb = authorRepository.findById(authorId).orElseThrow(
				() -> new NotFoundException(AUTHOR_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
		Book bookDb = bookRepository.findById(bookId).orElseThrow(
				() -> new NotFoundException(BookService.BOOK_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
		if (!authorDb.getBooks().contains(bookDb))
		{
			throw new NotFoundException(BookService.BOOK_NOT_FOUND, ServiceErrorCode.NOT_FOUND);
		}
		authorDb.getBooks().remove(bookDb);
		return authorRepository.save(authorDb);
	}
}
