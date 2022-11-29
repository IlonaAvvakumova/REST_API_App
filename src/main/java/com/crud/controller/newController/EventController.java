package com.crud.controller.newController;

import com.crud.model.Event;
import com.crud.service.EventService;

import javax.servlet.http.HttpServlet;
import java.util.List;

public class EventController extends HttpServlet {
    EventService eventService;

    public EventController() {
    }

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    public List<Event> getAll() {
        return eventService.getAll();
    }


    public Event getById(Integer id) {
        return eventService.getById(id);
    }

}
