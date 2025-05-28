package com.home.HomeApp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate eventDate;
    private String description;
    private String peopleInvolved;
    private String location;
    private String category;
    private String notes;

    // Constructors
    public Event() {
    }

    public Event(LocalDate eventDate, String description, String peopleInvolved, String location, String category, String notes) {
        this.eventDate = eventDate;
        this.description = description;
        this.peopleInvolved = peopleInvolved;
        this.location = location;
        this.category = category;
        this.notes = notes;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPeopleInvolved() {
        return peopleInvolved;
    }

    public void setPeopleInvolved(String peopleInvolved) {
        this.peopleInvolved = peopleInvolved;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
