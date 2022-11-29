package com.crud.repository.HibernateNew.Interface;

import com.crud.model.Event;

import java.util.List;

public interface EventRepository {
    List<Event> getAll();

    Event getById(Integer id);
}
