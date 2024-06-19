package com.example.alltickets.controllers;

import com.example.alltickets.models.Event;
import com.example.alltickets.services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testEvents() throws Exception {
        when(eventService.listEvents(anyString())).thenReturn(Collections.emptyList());
        when(eventService.getUserByPrincipal(any(Principal.class))).thenReturn(null);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("events"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testProductInfo() throws Exception {
        Event event = new Event();
        event.setId(1L);
        event.setImages(Collections.emptyList());

        when(eventService.getEventById(eq(1L))).thenReturn(event);

        mockMvc.perform(get("/event/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("event-info"))
                .andExpect(model().attributeExists("event"))
                .andExpect(model().attributeExists("images"));
    }

    @Test
    @WithMockUser
    public void testCreateEvent() throws Exception {
        mockMvc.perform(multipart("/event/create")
                        .file("file1", new byte[0])
                        .file("file2", new byte[0])
                        .file("file3", new byte[0])
                        .param("title", "Test Event")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Mockito.verify(eventService).saveEvent(any(Principal.class), any(Event.class), any(), any(), any());
    }

    @Test
    @WithMockUser
    public void testDeleteEvent() throws Exception {
        mockMvc.perform(post("/event/delete/1").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Mockito.verify(eventService).deleteEvent(eq(1L));
    }
}
