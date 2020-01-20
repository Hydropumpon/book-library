package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.model.Book;

import javax.transaction.Transactional;
import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();

    Author getOneAuthor(Long authorId);

    @Transactional
    Author addAuthor(Author author);

    @Transactional
    Author deleteAuthor(Long authorId);

    @Transactional
    Author updateAuthor(Long authorId, Author author);

    List<Book> getAuthorBooks(Long authorId);
}
