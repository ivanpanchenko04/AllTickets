package com.example.alltickets.services;

import com.example.alltickets.models.Event;
import com.example.alltickets.models.Image;
import com.example.alltickets.models.User;
import com.example.alltickets.repositories.EventRepository;
import com.example.alltickets.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EventServiceTest {

    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        eventService = new EventService(eventRepository, userRepository);
    }

    @Test
    public void testListEvents() {
        String title = "Test Event";
        Event event = new Event();
        event.setTitle(title);
        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        when(eventRepository.findByTitle(title)).thenReturn(eventList);

        List<Event> foundEvents = eventService.listEvents(title);

        assertEquals(1, foundEvents.size());
        assertEquals(title, foundEvents.get(0).getTitle());
        verify(eventRepository, times(1)).findByTitle(title);
        verify(eventRepository, never()).findAll();
    }

    @Test
    public void testDeleteEvent() {
        Long eventId = 1L;

        eventService.deleteEvent(eventId);

        verify(eventRepository, times(1)).deleteById(eventId);
    }

    @Test
    public void testGetEventById() {
        Long eventId = 1L;
        Event event = new Event();
        event.setId(eventId);
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        Event foundEvent = eventService.getEventById(eventId);

        assertNotNull(foundEvent);
        assertEquals(eventId, foundEvent.getId());
        verify(eventRepository, times(1)).findById(eventId);
    }

    @Test
    public void testGetUserByPrincipal() {
        String email = "test@example.com";
        Principal principal = () -> email;
        User user = new User();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(user);

        User foundUser = eventService.getUserByPrincipal(principal);

        assertNotNull(foundUser);
        assertEquals(email, foundUser.getEmail());
        verify(userRepository, times(1)).findByEmail(email);
    }
}
