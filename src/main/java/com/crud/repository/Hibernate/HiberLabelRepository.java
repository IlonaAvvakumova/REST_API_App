package com.crud.repository.Hibernate;

import com.crud.model.Label;
import com.crud.repository.LabelRepository;
import com.crud.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HiberLabelRepository implements LabelRepository {
    @Override
    public List<Label> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Label> from_labels = (List<Label>) session.createQuery("From Label").list();
        session.close();
        return from_labels;
    }

    @Override
    public Label getById(Integer id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Label label = session.get(Label.class, id);
        session.close();
        return label;
    }

    @Override
    public Label create(Label label) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(label);
        tx1.commit();
        session.close();
        return label;
    }

    @Override
    public Label update(Label label) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(label);
        tx1.commit();
        session.close();
        return label;
    }

    @Override
    public void deleteById(Integer id) {
        Label label = new Label();
        label.setId(id);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(label);
        tx1.commit();
        session.close();

    }
}
