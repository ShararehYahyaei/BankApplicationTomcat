package service.accuntService;

import dto.TransferDto;
import entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountServiceInterface {
    Account save(Account account);
    Optional<Account> findById(Long id);
    List<Account> findAll();
    List<Account> getAccountsByCustomerNumber(String customerNumber);
    void delete(Account account);
    void transfer(TransferDto transferDto);
    boolean isAccountNumberExisted(String accountNumber);

}
