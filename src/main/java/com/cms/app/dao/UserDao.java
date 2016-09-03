package com.cms.app.dao;

import com.cms.app.model.Role;
import com.cms.app.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by burusothman on 8/31/16.
 */
@Repository
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public UserDao() {
    }

    @Transactional
    public User save(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(user);
        tx.commit();
        session.close();
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> list() {
        Session session = this.sessionFactory.openSession();
        List<User> userList = session.createQuery("from User").list();
        session.close();
        return userList;
    }

    public void updateUserEnabled(User user) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("update User set enabled = :enabled" +
                " where username = :username");
        query.setParameter("enabled", user.isEnabled());
        query.setParameter("username", user.getUsername());
        int result = query.executeUpdate();
        session.close();
    }

    public User getUserByName(String userName) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("From User u where u.username = :username");
        query.setParameter("username", userName);
        User result = (User)query.list().get(0);
        session.close();
        return result;
    }

    public boolean checkUserName(String username) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("From User u where u.username = :username");
        query.setParameter("username", username);
        List<User> result = (List<User>)query.list();
        session.close();
        return result.size() > 0;
    }
}
