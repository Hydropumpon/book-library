package com.example.library.service.impl;

import com.example.library.common.exception.ConflictException;
import com.example.library.common.exception.ErrorMessage;
import com.example.library.common.exception.NotFoundException;
import com.example.library.common.exception.ServiceErrorCode;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    private AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getOneBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.BOOK_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        checkBookExistByTitle(book);
        List<Author> authors = getAuthorsById(book);
        authors.forEach(author -> author.addBook(book));
        book.setAuthors(new HashSet<>(authors));
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book deleteBook(Long bookId) {
        Book bookDb = getBookById(bookId);
        bookDb.getAuthors().forEach(author -> author.removeBook(bookDb));
        bookRepository.delete(bookDb);
        return bookDb;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getBookAuthors(Long bookId) {
        Book bookDb = getBookById(bookId);
        return new ArrayList<>(bookDb.getAuthors());
    }

    @Override
    @Transactional
    public Book updateBook(Long bookId, Book book) {
        Book bookDb = getBookById(bookId);
        checkTitleDuplicate(book, bookDb);
        return bookRepository.save(book);
    }

    private Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.BOOK_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
    }

    private void checkBookExistByTitle(Book book) {
        bookRepository.findByTitle(book.getTitle())
                      .ifPresent(b -> {
                          throw new ConflictException(ErrorMessage.BOOK_EXIST, ServiceErrorCode.ALREADY_EXIST);
                      });

    }

    private void checkTitleDuplicate(Book book, Book bookDb) {
        bookRepository.findByTitle(book.getTitle())
                      .filter(book1 -> book1.getId().equals(bookDb.getId()))
                      .orElseThrow(
                              () -> new ConflictException(ErrorMessage.BOOK_EXIST, ServiceErrorCode.ALREADY_EXIST)
                                  );


    }

    private List<Author> getAuthorsById(Book book) {
        return book.getAuthors()
                   .stream()
                   .map(author -> authorRepository.findById(author.getId()).orElseThrow(
                           () -> new NotFoundException(ErrorMessage.AUTHOR_NOT_FOUND, ServiceErrorCode.NOT_FOUND)))
                   .collect(Collectors.toList());
    }
}
