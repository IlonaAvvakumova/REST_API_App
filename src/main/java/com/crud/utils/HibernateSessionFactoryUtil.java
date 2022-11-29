package com.crud.utils;


import com.crud.model.Event;
import com.crud.model.FileDB;
import com.crud.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml").addAnnotatedClass(FileDB.class)
                        .addAnnotatedClass(Event.class)
                        .addAnnotatedClass(User.class).buildSessionFactory();

                          } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}
