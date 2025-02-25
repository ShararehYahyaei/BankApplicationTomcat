package service.customerService;


import config.SessionFactoryInstance;
import entity.Customer;
import entity.Employee;
import repository.customerRepository.CustomerRepoImpl;
import repository.customerRepository.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerServiceInter {

    private final CustomerRepository customerRepository = new CustomerRepoImpl();

    @Override
    public Customer save(Customer customer) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Customer saveCustomer = customerRepository.save(session, customer);
                session.getTransaction().commit();
                return saveCustomer;

            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
        }
        return null;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Optional<Customer> byId = customerRepository.findById(session, id);
                session.getTransaction().commit();
                return byId;
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();

            }
        }
        return null;
    }

    @Override
    public List<Customer> findAll() {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                List<Customer> customers = customerRepository.findAll(session);
                session.getTransaction().commit();
                return customers;
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();

            }
        }
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Optional<Customer> found = customerRepository.findById(session, customer.getId());
                if (found.isPresent()) {
                    found.get().setName(customer.getName());
                    found.get().setEmail(customer.getEmail());
                    found.get().setPhone(customer.getPhone());
                    customerRepository.save(session, found.get());
                    session.getTransaction().commit();
                    return found.get();
                } else {
                    System.out.println("Customer not found");
                    session.getTransaction().rollback();

                }
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();

            }
        }
        return null;

    }

    @Override
    public void delete(Customer customer) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                new Employee().getId();
                Optional<Customer> found = customerRepository.findById(session, customer.getId());

                if (found.isPresent()) {
                    customerRepository.delete(session, customer);
                    session.getTransaction().commit();
                } else {
                    System.out.println("Customer with id " + customer.getId() + " does not exist");
                    session.getTransaction().rollback();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
