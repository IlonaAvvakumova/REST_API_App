package com.crud.controller;

import com.crud.model.Event;
import com.crud.service.EventService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/api/v1/events")
public class EventController extends HttpServlet {
    EventService eventService = new EventService();

    public EventController() {
    }

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();

        if (req.getQueryString().equals("getAll")) {
            List<Event> events = eventService.getAll();
            printWriter.format("List Events " + events.toString());
            return;
        }

        Object param = req.getParameter("byid");
        if (req.getQueryString().equals("byid=" + param)) {
            Event byId = eventService.getById(Integer.parseInt(param.toString()));
            printWriter.format("Event " + byId);
            return;
        }
        printWriter.close();
    }


    public List<Event> getAll() {
        return eventService.getAll();
    }


    public Event getById(Integer id) {
        return eventService.getById(id);
    }

}
