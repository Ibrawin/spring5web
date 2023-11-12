package com.ibrawin.spring5web.repositories;

import com.ibrawin.spring5web.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
