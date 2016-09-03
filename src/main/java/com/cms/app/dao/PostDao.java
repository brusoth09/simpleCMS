package com.cms.app.dao;

import com.cms.app.model.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by burusothman on 8/31/16.
 */
@Repository
public class PostDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public Post save(Post post) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        post.setCreated(new Date());
        post.setModified(new Date());
        session.save(post);
        tx.commit();
        session.close();
        return post;
    }

    @Transactional
    public Post update(Post post) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(post);
        post.setModified(new Date());
        tx.commit();
        session.close();
        return post;
    }

    public List<Post> getAll() {
        Session session = this.sessionFactory.openSession();
        List<Post>  posts = session.createQuery("from Post").list();
        session.close();
        return posts;
    }

    public List<Post> getAllByUser(String userName) {
        Session session = this.sessionFactory.openSession();
        List<Post>  posts = session.createQuery("from Post p where p.username =:username")
                .setParameter("username",userName)
                .list();
        session.close();
        return posts;
    }

    public Post get(int id){
        Session session = this.sessionFactory.openSession();
        Post posts = (Post)session.get(Post.class, new Integer(id));
        session.close();
        return posts;
    }
}
