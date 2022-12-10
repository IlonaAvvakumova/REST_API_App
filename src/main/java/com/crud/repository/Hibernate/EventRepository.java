package com.crud.repository.Hibernate;

import com.crud.model.Event;

import java.util.List;

public interface EventRepository {
    List<Event> getAll();

    Event getById(Integer id);
}
