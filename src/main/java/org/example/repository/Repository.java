package org.example.repository;

import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Repository<T> {
    protected SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public void save(T someObject) {
        Session session = sessionFactory.openSession();
        session.persist(someObject);
        session.beginTransaction().commit();
        session.close();
    }
}
