package service.accuntService;

import entity.Account;
import entity.Customer;

import java.util.List;
import java.util.Optional;

public interface AccountServiceInterface {
    Account save(Account account);

    Optional<Account> findById(Long id);

    List<Account> findAll();

    Customer update(Account account);

    void delete(Account account);
}
