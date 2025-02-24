package repository;

import entity.Customer;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Session session, Customer customer);

    Optional<Customer> findById(Session session, Long id);

    List<Customer> findAll(Session session);

    Customer update(Session session, Customer customer);

    void delete(Session session, Customer customer);

}
