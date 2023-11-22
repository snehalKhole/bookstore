package com.bookstore.bookstore.common.services;

import com.bookstore.bookstore.model.entity.BookEntity;

import java.util.List;
/**
 * @author SnehalKhole
 * @version $Id: BookService.java $$
 */
public interface BookService {
    List<BookEntity> getAllBooks();

    BookEntity getBookById(Long id);

    BookEntity addBook(BookEntity book);

    BookEntity updateBook(Long id, BookEntity book);

    void deleteBook(Long id);
}
