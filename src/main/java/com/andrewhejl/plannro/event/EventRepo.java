package com.andrewhejl.plannro.event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event, Long>
{
    
}
