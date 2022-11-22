package com.crud.repository.Hibernate;

import com.crud.model.Writer;
import com.crud.repository.WriterRepository;
import com.crud.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HiberWriterRepository implements WriterRepository {

    @Override
    public List<Writer> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Writer> from_writers  =  (List<Writer>)session.createQuery("From Writer").getResultList();
        session.close();
        return from_writers;
    }

    @Override
    public Writer getById(Integer id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Writer writer =  session.get(Writer.class, id);
        session.close();
        return writer;
    }

    @Override
    public Writer create(Writer writer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(writer);
        tx1.commit();
        session.close();
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(writer);
        tx1.commit();
        session.close();
        return writer;
    }

    @Override
    public void deleteById(Integer id) {
        Writer writer = new Writer();
        writer.setId(id);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(writer);
        tx1.commit();
        session.close();
    }
}
