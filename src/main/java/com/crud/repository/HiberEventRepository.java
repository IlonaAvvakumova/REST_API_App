package com.crud.repository;


import com.crud.model.Event;
import com.crud.repository.Hibernate.EventRepository;
import com.crud.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;

import java.util.List;

public class HiberEventRepository implements EventRepository {

    @Override
    public List<Event> getAll() {
        List<Event> eventEntities;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            eventEntities = (List<Event>) session.createQuery("From Event").list();
        }
        return eventEntities;
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
