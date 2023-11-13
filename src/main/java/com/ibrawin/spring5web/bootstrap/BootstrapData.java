package com.ibrawin.spring5web.bootstrap;

import com.ibrawin.spring5web.domain.Author;
import com.ibrawin.spring5web.domain.Book;
import com.ibrawin.spring5web.domain.Publisher;
import com.ibrawin.spring5web.repositories.AuthorRepository;
import com.ibrawin.spring5web.repositories.BookRepository;
import com.ibrawin.spring5web.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher tobi = new Publisher("Tobi", "Ayan", "123 road", "London", "Greater London", "SE5 1AA");
        Author ibrahim = new Author("Ibra", "Ayan");
        Book livingLegend = new Book("Living legend", "123");

        ibrahim.getBooks().add(livingLegend);
        livingLegend.getAuthors().add(ibrahim);
        livingLegend.setPublishers(tobi);
        tobi.getBooks().add(livingLegend);

        authorRepository.save(ibrahim);
        publisherRepository.save(tobi);
        bookRepository.save(livingLegend);

        Author wale = new Author("Wale", "Ayan");
        Book ceo = new Book("CEO", "456");

        wale.getBooks().add(ceo);
        ceo.getAuthors().add(wale);
        ceo.setPublishers(tobi);
        tobi.getBooks().add(ceo);

        authorRepository.save(wale);
        bookRepository.save(ceo);
        publisherRepository.save(tobi);

        System.out.printf("The number of authors are: %d%n", authorRepository.count());
        System.out.printf("The number of books are: %d%n", bookRepository.count());
        System.out.printf("The number of publishers are: %d%n", publisherRepository.count());
        System.out.printf("The number of Tobis are: %d%n", tobi.getBooks().size());
    }
}
