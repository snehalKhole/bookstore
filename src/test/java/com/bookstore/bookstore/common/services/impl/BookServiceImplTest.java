package com.bookstore.bookstore.common.services.impl;

import com.bookstore.bookstore.common.constant.BookstoreResponseCode;
import com.bookstore.bookstore.common.exception.BookstoreException;
import com.bookstore.bookstore.model.entity.BookEntity;
import com.bookstore.bookstore.model.repository.BookRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class BookServiceImplTest {

    @Mock
    private BookRepo bookRepo;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllBooks() {
        List<BookEntity> mockBooks =new ArrayList<>();
        BookEntity bookEntity1=new BookEntity();
        bookEntity1.setId(1L);
        bookEntity1.setTitle("Book1");
        bookEntity1.setAuthor("author1");
        bookEntity1.setISBN("isbn1");
        bookEntity1.setGenre("history");

        BookEntity bookEntity2=new BookEntity();
        bookEntity2.setId(2L);
        bookEntity2.setTitle("Book2");
        bookEntity2.setAuthor("author2");
        bookEntity2.setISBN("isbn2");
        bookEntity2.setGenre("maths");
        mockBooks.add(bookEntity1);
        mockBooks.add(bookEntity2);

        when(bookRepo.findAll()).thenReturn(mockBooks);
        List<BookEntity> result = bookService.getAllBooks();
        assertEquals(mockBooks.size(), result.size());
        assertEquals(mockBooks, result);
    }
    @Test
    void testGetAllBooksException() {

        when(bookRepo.findAll()).thenThrow(new RuntimeException("Database error"));


        BookstoreException exception = assertThrows(
                BookstoreException.class,
                () -> bookService.getAllBooks(),
                "Expected BookstoreException but it was not thrown"
        );

        assertEquals(BookstoreResponseCode.UNABLE_TO_RETRIEVE_DATA.getMessage(), exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
    @Test
    void testGetBookById() {
        Long bookId = 1L;
        BookEntity bookEntity1=new BookEntity();
        bookEntity1.setTitle("Book1");
        bookEntity1.setAuthor("author1");
        bookEntity1.setISBN("isbn1");
        bookEntity1.setGenre("history");

        when(bookRepo.findById(bookId)).thenReturn(Optional.of(bookEntity1));


        BookEntity result = bookService.getBookById(bookId);

        assertEquals(bookEntity1, result);
    }
    @Test
    void testGetBookByIdNotFound() {
        Long bookId = 1L;

        when(bookRepo.findById(bookId)).thenReturn(Optional.empty());
        BookstoreException exception = assertThrows(
                BookstoreException.class,
                () -> bookService.getBookById(bookId),
                "Expected BookstoreException but it was not thrown"
        );
        assertEquals(BookstoreResponseCode.BOOK_NOT_FOUND.getMessage(), exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }
    @Test
    void testGetBookByIdException() {
        Long bookId = 1L;
        when(bookRepo.findById(bookId)).thenThrow(new RuntimeException("Database error"));
        BookstoreException exception = assertThrows(
                BookstoreException.class,
                () -> bookService.getBookById(bookId),
                "Expected BookstoreException but it was not thrown"
        );
        assertEquals(BookstoreResponseCode.UNABLE_TO_RETRIEVE_DATA.getMessage(), exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
    @Test
    void testAddBook() {
        BookEntity bookToSave=new BookEntity();
        bookToSave.setId(1L);
        bookToSave.setTitle("Book1");
        bookToSave.setAuthor("author1");
        bookToSave.setISBN("isbn1");
        bookToSave.setGenre("history");

        BookEntity savedBook=new BookEntity();
        bookToSave.setId(1L);
        bookToSave.setTitle("Book1");
        bookToSave.setAuthor("author1");
        bookToSave.setISBN("isbn1");
        bookToSave.setGenre("history");
        when(bookRepo.save(bookToSave)).thenReturn(savedBook);
        BookEntity result = bookService.addBook(bookToSave);
        assertEquals(savedBook, result);
    }
    @Test
    void testAddBookException() {
        BookEntity bookToSave=new BookEntity();
        bookToSave.setId(1L);
        bookToSave.setTitle("Book1");
        bookToSave.setAuthor("author1");
        bookToSave.setISBN("isbn1");
        bookToSave.setGenre("history");

        when(bookRepo.save(bookToSave)).thenThrow(new RuntimeException("Database error"));

        BookstoreException exception = assertThrows(
                BookstoreException.class,
                () -> bookService.addBook(bookToSave),
                "Expected BookstoreException but it was not thrown"
        );
        assertEquals(BookstoreResponseCode.UNABLE_TO_SAVE_DATA.getMessage(), exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
    @Test
    void testUpdateBook() {
        // Arrange
        Long bookId = 1L;
        BookEntity existingBook=new BookEntity();
        existingBook.setId(bookId);
        existingBook.setTitle("Book1");
        existingBook.setAuthor("author1");
        existingBook.setISBN("isbn1");
        existingBook.setGenre("history");

        BookEntity updatedBookData=new BookEntity();
        updatedBookData.setId(bookId);
        updatedBookData.setTitle("Updated Book");
        updatedBookData.setAuthor("New Author");
        updatedBookData.setISBN("New ISBN");

        BookEntity updatedBook=new BookEntity();
        updatedBook.setId(bookId);
        updatedBook.setTitle("Updated Book");
        updatedBook.setAuthor("New Author");
        updatedBook.setISBN("New ISBN");

        when(bookRepo.existsById(bookId)).thenReturn(true);
        when(bookRepo.save(updatedBookData)).thenReturn(updatedBook);

        BookEntity result = bookService.updateBook(bookId, updatedBookData);

        assertEquals(updatedBook, result);
    }
    @Test
    void testUpdateBookNotFound() {
        Long bookId = 1L;
        BookEntity updatedBookData=new BookEntity();
        updatedBookData.setId(bookId);
        updatedBookData.setTitle("Updated Book");
        updatedBookData.setAuthor("New Author");
        updatedBookData.setISBN("New ISBN");
        when(bookRepo.existsById(bookId)).thenReturn(false);

        BookstoreException exception = assertThrows(
                BookstoreException.class,
                () -> bookService.updateBook(bookId, updatedBookData),
                "Expected BookstoreException but it was not thrown"
        );
        assertEquals(BookstoreResponseCode.BOOK_NOT_FOUND.getMessage(), exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }
    @Test
    void testUpdateBookException() {
        Long bookId = 1L;
        BookEntity existingBook=new BookEntity();
        existingBook.setId(bookId);
        existingBook.setTitle("Book1");
        existingBook.setAuthor("author1");
        existingBook.setISBN("isbn1");
        existingBook.setGenre("history");

        BookEntity updatedBookData=new BookEntity();
        updatedBookData.setId(bookId);
        updatedBookData.setTitle("Updated Book");
        updatedBookData.setAuthor("New Author");
        updatedBookData.setISBN("New ISBN");
        when(bookRepo.existsById(bookId)).thenReturn(true);
        when(bookRepo.save(updatedBookData)).thenThrow(new RuntimeException("Database error"));

        BookstoreException exception = assertThrows(
                BookstoreException.class,
                () -> bookService.updateBook(bookId, updatedBookData),
                "Expected BookstoreException but it was not thrown"
        );
        assertEquals(BookstoreResponseCode.UNABLE_TO_UPDATE_DATA.getMessage(), exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
    @Test
    void testDeleteBook() {
        Long bookId = 1L;

        assertDoesNotThrow(() -> bookService.deleteBook(bookId));

    }
    @Test
    void testDeleteBookNotFound() {
        Long bookId = 1L;

        doThrow(EmptyResultDataAccessException.class).when(bookRepo).deleteById(bookId);

        BookstoreException exception = assertThrows(
                BookstoreException.class,
                () -> bookService.deleteBook(bookId),
                "Expected BookstoreException but it was not thrown"
        );

        assertEquals(BookstoreResponseCode.BOOK_NOT_FOUND.getMessage(), exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }
    @Test
    void testDeleteBookException() {
        Long bookId = 1L;

        doThrow(new RuntimeException("Database error")).when(bookRepo).deleteById(bookId);

        BookstoreException exception = assertThrows(
                BookstoreException.class,
                () -> bookService.deleteBook(bookId),
                "Expected BookstoreException but it was not thrown"
        );
        assertEquals(BookstoreResponseCode.UNABLE_TO_DELETE_DATA.getMessage(), exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
}