package service.customerService;


import dto.CustomerDto;
import dto.TransferDto;
import entity.Account;
import entity.Customer;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface CustomerServiceInter {
    Customer save(CustomerDto customer);
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    Customer update(Customer customer);
    void delete(Customer customer);
    Customer findByCustomerNumber(String customerNumber);
    Customer isUsernameExist(String username);
    Customer isEmailExist(  String email);
    Customer login (String customerNumber, String password);
    Customer isCustomerNumber(String customerNumber);


}
