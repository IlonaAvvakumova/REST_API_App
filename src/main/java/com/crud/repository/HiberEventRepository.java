package com.crud.repository;


import com.crud.model.Event;
import com.crud.repository.Interface.EventRepository;
import com.crud.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;

import java.util.List;

public class HiberEventRepository implements EventRepository {

    @Override
    public List<Event> getAll() {
        List<Event> events;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            events  = (List<Event>) session.createQuery("From Event").list();
        }
        return events;
    }

    @Override
    public Event getById(Integer id) {
       Event event;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            event = session.get(Event.class, id);
        }
        return event;
    }
}
