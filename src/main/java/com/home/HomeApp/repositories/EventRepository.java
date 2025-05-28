package com.home.HomeApp.repositories;

import com.home.HomeApp.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // Spring Data JPA will automatically implement basic CRUD operations
}
