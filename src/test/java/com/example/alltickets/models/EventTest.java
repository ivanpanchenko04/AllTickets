package com.example.alltickets.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    private Event event;

    @BeforeEach
    public void setup() {
        event = new Event();
    }

    @Test
    public void testEventCreation() {
        event.setId(1L);
        event.setTitle("Test Event");
        event.setDescription("This is a test event.");
        event.setPrice(100);
        event.setCity("Test City");

        assertEquals(1L, event.getId());
        assertEquals("Test Event", event.getTitle());
        assertEquals("This is a test event.", event.getDescription());
        assertEquals(100, event.getPrice());
        assertEquals("Test City", event.getCity());
    }

    @Test
    public void testAddImageToEvent() {
        Image image = new Image();
        image.setId(1L);

        event.addImageToEvent(image);

        List<Image> images = event.getImages();
        assertFalse(images.isEmpty());
        assertEquals(1, images.size());
        assertEquals(image, images.get(0));
        assertEquals(event, images.get(0).getEvent());
    }
}
