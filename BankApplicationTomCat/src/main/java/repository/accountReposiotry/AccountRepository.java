package repository.accountReposiotry;

import entity.Account;
import entity.Customer;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account save(Session session, Account account);

    Optional<Account> findById(Session session, Long id);

    List<Account> findAll(Session session);

    void delete(Session session, Account account);
}
