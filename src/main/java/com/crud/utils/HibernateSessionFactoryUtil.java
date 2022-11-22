package com.crud.utils;


import com.crud.model.Label;
import com.crud.model.Post;
import com.crud.model.Writer;
import com.crud.repository.Hibernate.HiberLabelRepository;
import com.crud.repository.Hibernate.HiberPostRepository;
import com.crud.repository.Hibernate.HiberWriterRepository;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml").addAnnotatedClass(Label.class)
                        .addAnnotatedClass(Post.class)
                        .addAnnotatedClass(Writer.class).buildSessionFactory();

                          } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}
