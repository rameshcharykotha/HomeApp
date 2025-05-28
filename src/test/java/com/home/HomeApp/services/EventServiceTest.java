package com.home.HomeApp.services;

import com.home.HomeApp.models.Event;
import com.home.HomeApp.repositories.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    void testCreateEvent() {
        Event eventToCreate = new Event(LocalDate.now(), "Test Event", "Person A", "Location X", "Category Y", "Notes Z");
        Event savedEvent = new Event(LocalDate.now(), "Test Event", "Person A", "Location X", "Category Y", "Notes Z");
        savedEvent.setId(1L); // Simulate ID generation

        when(eventRepository.save(any(Event.class))).thenReturn(savedEvent);

        Event result = eventService.createEvent(eventToCreate);

        assertNotNull(result);
        assertEquals(savedEvent.getId(), result.getId());
        assertEquals(eventToCreate.getDescription(), result.getDescription());
        verify(eventRepository).save(any(Event.class));
    }

    @Test
    void testGetEventById_Found() {
        Event sampleEvent = new Event(LocalDate.now(), "Sample Event", "Person B", "Location S", "Category T", "Notes U");
        sampleEvent.setId(1L);

        when(eventRepository.findById(1L)).thenReturn(Optional.of(sampleEvent));

        Optional<Event> result = eventService.getEventById(1L);

        assertTrue(result.isPresent());
        assertEquals(sampleEvent.getDescription(), result.get().getDescription());
        assertEquals(sampleEvent.getId(), result.get().getId());
    }

    @Test
    void testGetEventById_NotFound() {
        when(eventRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Event> result = eventService.getEventById(99L);

        assertFalse(result.isPresent());
    }
}
