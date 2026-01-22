package com.andrewhejl.plannro.eventcontroller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController
{
    private final EventRepo repo;

    public EventController(EventRepo repo) { this.repo = repo; }

    @GetMapping
    public List<Event> getAllEvents() { return repo.findAll(); }

    @PostMapping
    public Event createEvent(@RequestBody Event event) { return repo.save(event); }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventUpdate)
    {
        return repo.findById(id).map(event->
            {
                if(eventUpdate.getTitle() != null) event.setTitle(eventUpdate.getTitle());
                if(eventUpdate.getDescription() != null) event.setDescription(eventUpdate.getDescription());
                if(eventUpdate.getEventDate() != null) event.setEventDate(eventUpdate.getEventDate());
                if(eventUpdate.getStartTime() != null) event.setStartTime(eventUpdate.getStartTime());
                if(eventUpdate.getEndTime() != null) event.setEndTime(eventUpdate.getEndTime());

                Event saved = repo.save(event);
                return ResponseEntity.ok(saved);
            }
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
