package repository.customerRepository;

import entity.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class CustomerRepoImpl implements CustomerRepository {


    @Override
    public Customer save(Session session, Customer customer) {
        session.persist(customer);
        return customer;
    }

    @Override
    public Optional<Customer> findById(Session session, Long id) {
        Customer found = session.byId(Customer.class).load(id);
        return Optional.ofNullable(found);
    }

    @Override
    public List<Customer> findAll(Session session) {
        return session.createQuery("from Customer").list();
    }

    @Override
    public void delete(Session session, Customer customer) {
        session.delete(customer);
    }

    @Override
    public Customer findByCustomerNumber(Session session, String customerNumber) {
        Query<Customer> query = session.createQuery("FROM Customer c WHERE c.customerNumber = :customerNumber", Customer.class);
        query.setParameter("customerNumber", customerNumber);
        return query.uniqueResult();
    }

    @Override
    public Customer login(Session session, String userName, String password) {
        return session.createQuery("FROM Customer c WHERE c.userName = :userName AND c.password = :password", Customer.class)
                .setParameter("userName", userName)
                .setParameter("password", password)
                .uniqueResult();

    }

    @Override
    public Customer isUsernameExist(Session session, String username) {
        return session.createQuery("FROM Customer c WHERE c.userName = :userName", Customer.class)
                .setParameter("userName", username)
                .uniqueResult();
    }

    @Override
    public Customer isEmailExist(Session session, String email) {
        return session.createQuery("FROM Customer c WHERE c.email = :email", Customer.class)
                .setParameter("email", email)
                .uniqueResult();
    }



}
