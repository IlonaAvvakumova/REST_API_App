package com.crud.controller;

import com.crud.model.Event;
import com.crud.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/api/v1/events/*")
public class EventController extends HttpServlet {
    private final EventService eventService;

    public EventController() {
        eventService = new EventService();
    }

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        if (req.getRequestURI().equals("/REST_API_App/api/v1/events")) {
            List<Event> events = eventService.getAll();
            for (Event u : events
            ) {
                String jsonString = objectMapper.writeValueAsString(u);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                printWriter.print(jsonString);
                printWriter.flush();
            }
            return;
        }
        int index = req.getRequestURI().lastIndexOf("/");
        String s = req.getRequestURI().substring(index + 1);
        Integer id = Integer.parseInt(s);
        if (req.getRequestURI().equals("/REST_API_App/api/v1/events/" + id)) {
            Event byId = eventService.getById(id);
            String jsonString = objectMapper.writeValueAsString(byId);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            printWriter.print(jsonString);
            printWriter.flush();
            return;
        }
        printWriter.close();
    }
}
