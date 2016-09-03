package com.cms.app.dao;

import com.cms.app.model.Role;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ragu on 9/1/16.
 */
@Repository
public class RoleDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public Role saveRole(Role role) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(role);
        tx.commit();
        session.close();
        return role;
    }

    public Boolean isAdminUser(String userName){
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Role r where r.username = :username and r.role = 'ROLE_ADMIN'");
        query.setParameter("username", userName);
        Role role = (Role)query.uniqueResult();
        session.close();
        return role != null;
    }
}
