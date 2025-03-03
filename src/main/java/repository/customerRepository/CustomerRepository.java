package repository.customerRepository;

import entity.Customer;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Session session, Customer customer);
    Optional<Customer> findById(Session session, Long id);
    List<Customer> findAll(Session session);
    void delete(Session session, Customer customer);
    Customer findByCustomerNumber(Session session, String customerNumber);
    Customer login(Session session, String userName, String password);
    Customer isUsernameExist(Session session,  String username);
    Customer isEmailExist( Session session,  String email);


}
