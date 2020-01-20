package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();

    Author getOneAuthor(Long authorId);

    Author addAuthor(Author author);

    Author deleteAuthor(Long authorId);

    Author updateAuthor(Long authorId, Author author);

    List<Book> getAuthorBooks(Long authorId);

    Page<Author> getAuthorPages(Pageable pageable);
}
