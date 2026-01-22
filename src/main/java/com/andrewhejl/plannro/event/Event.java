package com.andrewhejl.plannro.event;

import com.andrewhejl.plannro.user.User;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")

public class Event 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = true)
    private String title;

    private String description;

    @Column(nullable = true)
    private LocalDate eventDate;

    @Column(nullable = true)
    private LocalTime startTime = LocalTime.of(9, 0);

    @Column(nullable = true)
    private LocalTime endTime;

    protected Event() {}

    public Event(User user, String title, String description, LocalDate eventDate, LocalTime startTime, LocalTime endTime)
    {
        this.user = user;
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() { return this.id; }
    public String getTitle() { return this.title; }
    public String getDescription() { return this.description; }
    public LocalDate getEventDate() { return this.eventDate; }
    public LocalTime getStartTime() { return this.startTime; }
    public LocalTime getEndTime() { return this.endTime; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate ;}
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
}