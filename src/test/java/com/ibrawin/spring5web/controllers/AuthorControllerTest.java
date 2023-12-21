package com.ibrawin.spring5web.controllers;

import com.ibrawin.spring5web.domain.Author;
import com.ibrawin.spring5web.repositories.AuthorRepository;
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

class AuthorControllerTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private Model model;

    private AuthorController authorController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authorController = new AuthorController(authorRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
    }

    @Test
    void getAuthors() {
        when(authorRepository.findAll()).thenReturn(List.of(new Author("Ibra", "A"), new Author("Ibz", "B")));
        ArgumentCaptor<Iterable<Author>> captor = ArgumentCaptor.forClass(Iterable.class);

        String viewName = authorController.getAuthors(model);

        assertEquals("authors/list", viewName);
        verify(authorRepository, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("authors"), captor.capture());
        Iterable<Author> authors = captor.getValue();
        assertEquals(2, StreamSupport.stream(authors.spliterator(), false).count());
    }

    @Test
    void mockMvcAuthors() throws Exception {
        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(view().name("authors/list"));
    }
}