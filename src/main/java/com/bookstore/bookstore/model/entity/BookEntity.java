package com.bookstore.bookstore.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.util.Date;
/**
 * @author SnehalKhole
 * @version $Id: BookEntity.java $$
 */
@Entity
@Table(name = BookEntity.Columns.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity extends BaseEntity implements Serializable {
    public interface Columns extends BaseEntity.Columns {
        String TABLE_NAME = "book_details";
        String TITLE="title";
        String AUTHOR="author";
        String ISBN="ISBN";
        String PUBLISHED_DATE="publishedDate";

        String GENRE="genre";
    }

    @Column(name = Columns.TITLE)
    private String title;

    @Column(name = Columns.AUTHOR)
    private String author;

    @Column(name = Columns.ISBN)
    private String ISBN;

    @Column(name = Columns.PUBLISHED_DATE)
    private Date publishedDate;

    @Column(name = Columns.GENRE)
    private String genre;
    }

