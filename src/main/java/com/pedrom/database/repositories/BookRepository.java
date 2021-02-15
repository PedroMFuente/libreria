package com.pedrom.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrom.database.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
