package com.bookstore.bookstore.common.exception;


import com.bookstore.bookstore.common.constant.BookstoreResponseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;


/**
 * @author Snehal Khole
 * @version $Id: BookstoreException.java
 */
@Getter
public class BookstoreException extends RuntimeException {

    private final String code;

    private final HttpStatus httpStatus;

    public BookstoreException(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public BookstoreException(BookstoreResponseCode responseCode) {
        this(responseCode.getCode(), responseCode.getMessage(), HttpStatus.BAD_REQUEST);
    }

    public BookstoreException(BookstoreResponseCode responseCode, HttpStatus httpStatus) {
        this(responseCode.getCode(), responseCode.getMessage(), httpStatus);
    }

}
