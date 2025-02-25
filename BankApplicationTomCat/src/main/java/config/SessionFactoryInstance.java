package config;

import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryInstance {
    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(Branch.class)
                .addAnnotatedClass(Card.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Transaction.class)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Manager.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
