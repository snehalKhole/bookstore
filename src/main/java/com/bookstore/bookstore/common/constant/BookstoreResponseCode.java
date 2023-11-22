package com.bookstore.bookstore.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * @author SnehalKhole
 * @version $Id: BookstoreResponseCode.java $$
 */
@AllArgsConstructor
@Getter
public enum BookstoreResponseCode {
    UNABLE_TO_RETRIEVE_DATA("BS100", "Error occurred while retrieving all books."),
    BOOK_NOT_FOUND("BS101","Book not found with given ID"),
    UNABLE_TO_SAVE_DATA("BS102","Unable to add book"),
    UNABLE_TO_UPDATE_DATA("BS103","Unable to update book with given ID"),
    UNABLE_TO_DELETE_DATA("BS104","Unable to delete book with given ID")


    ;

    private String code;
    private String message;
}
