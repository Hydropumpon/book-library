package com.example.library.service;

import com.example.library.common.exception.ConflictException;
import com.example.library.common.exception.ErrorMessage;
import com.example.library.common.exception.NotFoundException;
import com.example.library.common.exception.ServiceErrorCode;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AuthorService
{
	private AuthorRepository authorRepository;

	@Autowired
	public AuthorService(AuthorRepository authorRepository)
	{
		this.authorRepository = authorRepository;
	}

	public List<Author> getAllAuthors()
	{
		return authorRepository.findAll();
	}

	public Author getOneAuthor(Long authorId)
	{
		return authorRepository.findById(authorId).orElseThrow(
				() -> new NotFoundException(ErrorMessage.AUTHOR_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
	}

	@Transactional
	public Author addAuthor(Author author)
	{
		checkAuthorExistByName(author);
		return authorRepository.save(author);
	}

	@Transactional
	public Author deleteAuthor(Long authorId)
	{
		Author authorDb = getAuthorById(authorId);
		authorDb.getBooks().forEach(book -> book.removeAuthor(authorDb));
		authorRepository.delete(authorDb);
		return authorDb;
	}

	@Transactional
	public Author updateAuthor(Long authorId, Author author)
	{
		Author authorDb = getAuthorById(authorId);
		checkNameDuplicate(author, authorDb);
		return authorRepository.save(author);
	}

	public List<Book> getAuthorBooks(Long authorId)
	{
		Author author = getAuthorById(authorId);
		return new ArrayList<>(author.getBooks());
	}

	private void checkNameDuplicate(Author author, Author authorDb)
	{
		Optional<Author> authorWithSameName = authorRepository.findByName(author.getName());
		if ((authorWithSameName.isPresent()) && (!authorDb.getId().equals(authorWithSameName.get().getId())))
		{
			throw new ConflictException(ErrorMessage.AUTHOR_EXIST, ServiceErrorCode.ALREADY_EXIST);
		}
	}

	private Author getAuthorById(Long authorId)
	{
		return authorRepository.findById(authorId).orElseThrow(
				() -> new NotFoundException(ErrorMessage.AUTHOR_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
	}

	private void checkAuthorExistByName(Author author)
	{
		Optional<Author> authorDb = authorRepository.findByName(author.getName());
		if (authorDb.isPresent())
		{
			throw new ConflictException(ErrorMessage.AUTHOR_EXIST, ServiceErrorCode.ALREADY_EXIST);
		}
	}

}
