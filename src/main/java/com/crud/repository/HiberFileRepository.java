package com.crud.repository;

import com.crud.model.FileDB;
import com.crud.model.oldModel.Label;
import com.crud.repository.Interface.FileRepository;
import com.crud.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HiberFileRepository implements FileRepository {

    @Override
    public List<FileDB> getAll() {
        List<FileDB> files;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
         files  = (List<FileDB>) session.createQuery("From FileDB").list();
        }
        return files;
    }

    @Override
    public FileDB getById(Integer id) {
        FileDB fileDB;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            fileDB = session.get(FileDB.class, id);
        }
        return fileDB;
    }

    @Override
    public FileDB create(FileDB fileDB) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()
        ) {
            Transaction tx1 = session.beginTransaction();
            session.save(fileDB);
            tx1.commit();

        }
        return fileDB;

    }

    @Override
    public void deleteById(Integer id) {
        FileDB file = new FileDB();
        file.setId(id);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.delete(file);
            tx1.commit();
        }
    }
}