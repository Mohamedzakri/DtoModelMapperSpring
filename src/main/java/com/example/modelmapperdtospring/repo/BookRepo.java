package com.example.modelmapperdtospring.repo;

import com.example.modelmapperdtospring.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {


}
