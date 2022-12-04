package com.crud.repository;

import com.crud.model.FileEntity;
import com.crud.repository.Interface.FileRepository;
import com.crud.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HiberFileRepository implements FileRepository {

    @Override
    public List<FileEntity> getAll() {
        List<FileEntity> files;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
         files  = (List<FileEntity>) session.createQuery("From FileEntity").list();
        }
        return files;
    }

    @Override
    public FileEntity getById(Integer id) {
        FileEntity fileEntity;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            fileEntity = session.get(FileEntity.class, id);
        }
        return fileEntity;
    }

    @Override
    public FileEntity create(FileEntity fileEntity) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()
        ) {
            Transaction tx1 = session.beginTransaction();
            session.save(fileEntity);
            tx1.commit();

        }
        return fileEntity;

    }

    @Override
    public void deleteById(Integer id) {
        FileEntity file = new FileEntity();
        file.setId(id);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.delete(file);
            tx1.commit();
        }
    }
}
