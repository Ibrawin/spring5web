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
        addEntities(ibrahim, livingLegend, tobi);
        saveEntities(ibrahim, livingLegend, tobi);

        Author wale = new Author("Wale", "Ayan");
        Book ceo = new Book("CEO", "456");
        addEntities(wale, ceo, tobi);
        saveEntities(wale, ceo, tobi);

        System.out.printf("The number of authors are: %d%n", authorRepository.count());
        System.out.printf("The number of books are: %d%n", bookRepository.count());
        System.out.printf("The number of publishers are: %d%n", publisherRepository.count());
        System.out.printf("The number of Tobis are: %d%n", tobi.getBooks().size());
    }

    private void addEntities(Author author, Book book, Publisher publisher) {
        author.getBooks().add(book);
        book.getAuthors().add(author);
        book.setPublishers(publisher);
        publisher.getBooks().add(book);
    }

    private void saveEntities(Author author, Book book, Publisher publisher) {
        authorRepository.save(author);
        publisherRepository.save(publisher);
        bookRepository.save(book);
    }

}
