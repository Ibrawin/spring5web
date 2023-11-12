package com.ibrawin.spring5web.repositories;

import com.ibrawin.spring5web.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
