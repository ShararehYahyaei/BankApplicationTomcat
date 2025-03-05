package service.accuntService;

import dto.AccountDto;
import dto.TransferDto;
import entity.Account;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface AccountServiceInterface {
    Account save(AccountDto accountDto);
    Optional<Account> findById(Long id);
    List<Account> findAll();
    List<Account> getAccountsByCustomerNumber(String customerNumber);
    void delete(Account account);
    void transfer(TransferDto transferDto);
    boolean isAccountNumberExisted(String accountNumber);
    Account getAccountByAccountNumber( String accountNumber);
    Account update(Account account);

}
