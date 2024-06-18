package com.example.alltickets.repositories;

import com.example.alltickets.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByTitle(String title);
}
