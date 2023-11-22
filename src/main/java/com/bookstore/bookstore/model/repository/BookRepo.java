package com.bookstore.bookstore.model.repository;

import com.bookstore.bookstore.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author SnehalKhole
 * @version $Id: BookRepo.java $$
 */
@Repository
public interface BookRepo extends JpaRepository<BookEntity,Long> {
}
