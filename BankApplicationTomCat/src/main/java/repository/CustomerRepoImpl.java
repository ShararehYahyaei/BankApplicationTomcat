package repository;

import entity.Customer;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class CustomerRepoImpl implements CustomerRepository {


    @Override
    public Customer save(Session session, Customer customer) {
        return null;
    }

    @Override
    public Optional<Customer> findById(Session session, Long id) {
        return Optional.empty();
    }

    @Override
    public List<Customer> findAll(Session session) {
        return List.of();
    }

    @Override
    public void delete(Session session, Customer customer) {

    }

    @Override
    public Customer update(Session session, Customer customer) {
        return null;
    }
}
