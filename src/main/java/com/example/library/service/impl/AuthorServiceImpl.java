package com.example.library.service.impl;

import com.example.library.common.exception.ConflictException;
import com.example.library.common.exception.ErrorMessage;
import com.example.library.common.exception.NotFoundException;
import com.example.library.common.exception.ServiceErrorCode;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Author getOneAuthor(Long authorId) {
        return getAuthorById(authorId);
    }

    @Override
    @Transactional
    public Author addAuthor(Author author) {
        checkAuthorExistByName(author);
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public Author deleteAuthor(Long authorId) {
        Author authorDb = getAuthorById(authorId);
        authorDb.getBooks().forEach(book -> book.removeAuthor(authorDb));
        authorRepository.delete(authorDb);
        return authorDb;
    }

    @Override
    @Transactional
    public Author updateAuthor(Long authorId, Author author) {
        Author authorDb = getAuthorById(authorId);
        checkNameDuplicate(author, authorDb);
        return authorRepository.save(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAuthorBooks(Long authorId) {
        Author author = getAuthorById(authorId);
        return new ArrayList<>(author.getBooks());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Author> getAuthorPages(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    private void checkNameDuplicate(Author author, Author authorDb) {
        authorRepository.findByName(author.getName())
                        .filter(auth -> auth.getId().equals(authorDb.getId()))
                        .orElseThrow(
                                () -> new ConflictException(ErrorMessage.AUTHOR_EXIST, ServiceErrorCode.ALREADY_EXIST)
                                    );
    }

    private Author getAuthorById(Long authorId) {
        return authorRepository.findById(authorId)
                               .orElseThrow(() -> new NotFoundException(ErrorMessage.AUTHOR_NOT_FOUND,
                                                                        ServiceErrorCode.NOT_FOUND));
    }

    private void checkAuthorExistByName(Author author) {
        authorRepository.findByName(author.getName())
                        .orElseThrow(
                                () -> new ConflictException(ErrorMessage.AUTHOR_EXIST, ServiceErrorCode.ALREADY_EXIST));
    }

}
