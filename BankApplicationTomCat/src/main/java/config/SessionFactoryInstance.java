package config;

import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryInstance {
    public static SessionFactory getSessionFactory;

    static {
        getSessionFactory = new Configuration().configure()
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(Branch.class)
                .addAnnotatedClass(Card.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Transaction.class)
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

    }


}
