package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getOneBook(Long bookId);

    Book addBook(Book book);

    Book deleteBook(Long bookId);

    List<Author> getBookAuthors(Long bookId);

    Book updateBook(Long bookId, Book book);
}
