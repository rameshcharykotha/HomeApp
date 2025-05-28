package com.home.HomeApp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.home.HomeApp.models.Event;
import com.home.HomeApp.services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // Register JavaTimeModule for proper LocalDate serialization/deserialization
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testCreateEvent_Success() throws Exception {
        LocalDate eventDate = LocalDate.of(2024, 3, 15);
        Event eventToCreate = new Event(eventDate, "New Year Party", "John Doe", "Office", "Celebration", "Annual event");
        Event savedEvent = new Event(eventDate, "New Year Party", "John Doe", "Office", "Celebration", "Annual event");
        savedEvent.setId(1L);

        when(eventService.createEvent(any(Event.class))).thenReturn(savedEvent);

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventToCreate)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.description").value("New Year Party"))
                .andExpect(jsonPath("$.eventDate").value("2024-03-15"));
    }

    @Test
    void testGetEventById_Found() throws Exception {
        LocalDate eventDate = LocalDate.of(2024, 7, 20);
        Event sampleEvent = new Event(eventDate, "Summer Picnic", "Jane Smith", "Central Park", "Outdoor", "Bring snacks");
        sampleEvent.setId(1L);

        when(eventService.getEventById(1L)).thenReturn(Optional.of(sampleEvent));

        mockMvc.perform(get("/api/events/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.description").value("Summer Picnic"))
                .andExpect(jsonPath("$.location").value("Central Park"))
                .andExpect(jsonPath("$.eventDate").value("2024-07-20"));
    }

    @Test
    void testGetEventById_NotFound() throws Exception {
        when(eventService.getEventById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/events/99"))
                .andExpect(status().isNotFound());
    }
}
