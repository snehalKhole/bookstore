package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.common.services.BookService;
import com.bookstore.bookstore.common.constant.RestMappings;
import com.bookstore.bookstore.model.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @author SnehalKhole
 * @version $Id: BookController.java $$
 */
@Validated
@RestController
@RequestMapping(RestMappings.BookDetailsMappings.BASE)
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(RestMappings.BookDetailsMappings.GET_ALL_BOOKS)
    public List<BookEntity> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(RestMappings.ID_PATH_VARIABLE)
    public BookEntity getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping(RestMappings.BookDetailsMappings.ADD_BOOK)
    public BookEntity addBook(@RequestBody BookEntity book) {
        return bookService.addBook(book);
    }
    @PutMapping(RestMappings.BookDetailsMappings.UPDATE)
    public BookEntity updateBook(@PathVariable Long id, @RequestBody BookEntity book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping(RestMappings.ID_PATH_VARIABLE)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
