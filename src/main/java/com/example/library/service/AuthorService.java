package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    Page<Author> getAuthorPages(Pageable pageable);
}
