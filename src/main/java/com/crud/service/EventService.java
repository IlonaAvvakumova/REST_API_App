package com.crud.service;

import com.crud.model.Event;
import com.crud.repository.HiberEventRepository;
import com.crud.repository.Hibernate.EventRepository;

import java.util.List;

public class EventService {

    EventRepository eventRepository = new HiberEventRepository();
    public EventService() {
    }

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public List<Event> getAll() {
        return eventRepository.getAll();
    }


    public Event getById(Integer id) {
        return eventRepository.getById(id);
    }
}
