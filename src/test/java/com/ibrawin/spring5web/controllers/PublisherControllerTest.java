package com.ibrawin.spring5web.controllers;

import com.ibrawin.spring5web.domain.Publisher;
import com.ibrawin.spring5web.repositories.PublisherRepository;
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

class PublisherControllerTest {

    @Mock
    private Model model;

    @Mock
    private PublisherRepository publisherRepository;

    private PublisherController publisherController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        publisherController = new PublisherController(publisherRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(publisherController).build();
    }

    @Test
    void getPublishers() {
        when(publisherRepository.findAll())
                .thenReturn(List.of(new Publisher("John", "Doe", "123", "456", "789", "SE21 4JH")));
        ArgumentCaptor<List<Publisher>> captor = ArgumentCaptor.forClass(List.class);

        String viewName = publisherController.getPublishers(model);

        assertEquals("publishers/list", viewName);
        verify(publisherRepository, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("publishers"), captor.capture());
        Iterable<Publisher> publishers = captor.getValue();
        assertEquals(1, StreamSupport.stream(publishers.spliterator(), false).count());
    }

    @Test
    void mockMvcPublishers() throws Exception {
        mockMvc.perform(get("/publishers"))
                .andExpect(status().isOk())
                .andExpect(view().name("publishers/list"));
    }
}