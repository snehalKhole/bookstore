package com.bookstore.bookstore.common.services.impl;

import com.bookstore.bookstore.common.services.BookService;
import com.bookstore.bookstore.common.constant.BookstoreResponseCode;
import com.bookstore.bookstore.common.exception.BookstoreException;
import com.bookstore.bookstore.model.entity.BookEntity;
import com.bookstore.bookstore.model.repository.BookRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @author SnehalKhole
 * @version $Id: BookServiceImpl.java $$
 */
@Service
public class BookServiceImpl implements BookService {
    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);


    @Autowired
    private BookRepo bookRepo;

    /**
     * @param
     * @return List<BookEntity>
     * @author Snehal Khole
     * @comment this will get all books from db
     */
    @Override
    public List<BookEntity> getAllBooks() {
        try {
            List<BookEntity> books = bookRepo.findAll();
            logger.info("Retrieved all books: {}", books);
            return books;
        } catch (Exception ex) {
            if (ex instanceof BookstoreException) {
                throw ex;
            }
            throw new BookstoreException(BookstoreResponseCode.UNABLE_TO_RETRIEVE_DATA, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param id
     * @return BookEntity
     * @author Snehal Khole
     * @comment this will get book by id from db
     */
    @Override
    public BookEntity getBookById(Long id) {
        try {
            BookEntity book = bookRepo.findById(id)
                    .orElseThrow(() -> new BookstoreException(
                            BookstoreResponseCode.BOOK_NOT_FOUND, HttpStatus.NOT_FOUND));
            logger.info("Retrieved book by ID {}: {}", id, book);
            return book;
        } catch (Exception ex) {
            if (ex instanceof BookstoreException) {
                throw ex;
            }
            throw new BookstoreException(BookstoreResponseCode.UNABLE_TO_RETRIEVE_DATA, HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * @param book
     * @return BookEntity
     * @author Snehal Khole
     * @comment this will add book in db
     */
    @Override
    public BookEntity addBook(BookEntity book) {
        try {
            BookEntity savedBook = bookRepo.save(book);
            logger.info("Added new book: {}", savedBook);
            return savedBook;
        } catch (Exception ex) {
            throw new BookstoreException(
                    BookstoreResponseCode.UNABLE_TO_SAVE_DATA, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param book
     * @param id
     * @return BookEntity
     * @author Snehal Khole
     * @comment this will update book in db
     */
    @Override
    public BookEntity updateBook(Long id, BookEntity book) {
        try {
            if (bookRepo.existsById(id)) {
                book.setId(id);
                BookEntity updatedBook = bookRepo.save(book);
                logger.info("Updated book with ID {}: {}", id, updatedBook);
                return updatedBook;
            } else {
                throw new BookstoreException(
                        BookstoreResponseCode.BOOK_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            throw new BookstoreException(
                    BookstoreResponseCode.UNABLE_TO_UPDATE_DATA, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param id
     * @return void
     * @author Snehal Khole
     * @comment this will delete book from db
     */
    @Override
    public void deleteBook(Long id) {
        try {
            bookRepo.deleteById(id);
            logger.info("Deleted book with ID: {}", id);
        } catch (EmptyResultDataAccessException ex) {
            throw new BookstoreException(
                    BookstoreResponseCode.BOOK_NOT_FOUND, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            throw new BookstoreException(
                    BookstoreResponseCode.UNABLE_TO_DELETE_DATA, HttpStatus.BAD_REQUEST);
        }
    }
}
