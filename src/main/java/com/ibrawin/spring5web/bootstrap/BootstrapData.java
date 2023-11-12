package com.ibrawin.spring5web.bootstrap;

import com.ibrawin.spring5web.domain.Author;
import com.ibrawin.spring5web.domain.Book;
import com.ibrawin.spring5web.repositories.AuthorRepository;
import com.ibrawin.spring5web.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author ibrahim = new Author("Ibra", "Ayan");
        Book livingLegend = new Book("Living legend", "123");
        ibrahim.getBooks().add(livingLegend);
        livingLegend.getAuthors().add(ibrahim);

        authorRepository.save(ibrahim);
        bookRepository.save(livingLegend);

        Author wale = new Author("Wale", "Ayan");
        Book ceo = new Book("CEO", "456");
        wale.getBooks().add(ceo);
        ceo.getAuthors().add(wale);

        authorRepository.save(wale);
        bookRepository.save(ceo);

        System.out.printf("The number of authors are: %d%n", authorRepository.count());
        System.out.printf("The number of books are: %d%n", bookRepository.count());
    }
}
