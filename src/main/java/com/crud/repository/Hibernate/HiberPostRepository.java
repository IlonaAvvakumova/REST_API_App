package com.crud.repository.Hibernate;

import com.crud.model.Post;
import com.crud.model.PostStatus;
import com.crud.repository.PostRepository;
import com.crud.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HiberPostRepository implements PostRepository {
    @Override
    public List<Post> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Post> from_posts  =  (List<Post>)  session.createQuery("From Post").list();
        session.close();
        return from_posts;
    }

    @Override
    public Post getById(Integer id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Post post =  session.get(Post.class, id);
        session.close();
        return post;
    }

    @Override
    public Post create(Post post) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(post);
        tx1.commit();
        session.close();
        return post;
    }

    @Override
    public Post update(Post post) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(post);
        tx1.commit();
        session.close();
        return post;
    }

    @Override
    public void deleteById(Integer id) {
        Post post = new Post();
        post.setId(id);
        post.setStatus(PostStatus.DELETED);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(post);
        tx1.commit();
        session.close();

    }
}
