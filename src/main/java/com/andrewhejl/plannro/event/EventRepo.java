package com.andrewhejl.plannro.event;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event, Long> 
{
    Event save(Event event);
    Optional<Event> findById(Long id);
}
