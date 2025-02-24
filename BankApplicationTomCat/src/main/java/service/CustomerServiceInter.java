package service;


import entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerServiceInter {
    Customer save(Customer customer);

    Optional<Customer> findById( Long id);

    List<Customer> findAll();

    Customer update( Customer customer);

    void delete( Customer customer);

}
