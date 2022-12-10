package com.crud.repository;

import com.crud.model.User;
import com.crud.repository.Hibernate.UserRepository;
import com.crud.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class HiberUserRepository implements UserRepository {

    @Override
    public List<User> getAll() {
        List<User> userEntities;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            userEntities = (List<User>) session.createQuery("From User").list();
        }
        return userEntities;
    }

    @Override
    public User getById(Integer id) {
        User user;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            user = session.get(User.class, id);
        }
        return user;
    }

    @Override
    public User create(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()
        ) {
            Transaction tx1 = session.beginTransaction();
            session.save(user);
            tx1.commit();

        }
        return user;
    }

    @Override
    public User update(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.update(user);
            tx1.commit();
        }

        return user;
    }

    @Override
    public void deleteById(Integer id) {
        User user = new User();
        user.setId(id);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.delete(user);
            tx1.commit();
        }
    }
}
