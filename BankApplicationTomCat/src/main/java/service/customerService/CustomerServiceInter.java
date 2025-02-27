package service.customerService;


import dto.CustomerDto;
import entity.Account;
import entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerServiceInter {
    Customer save(CustomerDto customer);

    Optional<Customer> findById(Long id);

    List<Customer> findAll();

    Customer update(Customer customer);

    void delete(Customer customer);

}
