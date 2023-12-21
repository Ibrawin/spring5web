package com.ibrawin.spring5web.controllers;

import com.ibrawin.spring5web.domain.Book;
import com.ibrawin.spring5web.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class BookControllerTest {

    @Mock
    private Model model;

    @Mock
    private BookRepository bookRepository;

    private BookController bookController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookController = new BookController(bookRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void getBooks() {
        when(bookRepository.findAll())
                .thenReturn(List.of(new Book("Legend", "123"), new Book("Class", "456")));
        ArgumentCaptor<Iterable<Book>> captor = ArgumentCaptor.forClass(Iterable.class);

        String viewName = bookController.getBooks(model);

        assertEquals("books/list", viewName);
        verify(bookRepository, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("books"), captor.capture());
        Iterable<Book> books = captor.getValue();
        assertEquals(2, StreamSupport.stream(books.spliterator(), false).count());
    }

    @Test
    void mockMvcBooks() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/list"));
    }
}