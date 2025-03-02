package repository.accountReposiotry;

import entity.Account;
import entity.Customer;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepository {

    @Override
    public Account save(Session session, Account account) {
        session.persist(account);
        return account;
    }

    @Override
    public Optional<Account> findById(Session session, Long id) {
        Account found = session.byId(Account.class).load(id);
        return Optional.ofNullable(found);
    }

    @Override
    public List<Account> findAll(Session session) {
        return session.createQuery("from Account").list();
    }

    @Override
    public void delete(Session session, Account account) {
        session.delete(account);
    }

    @Override
    public Account getAccountByCustomerNumber(Session session, String customerNumber) {
        return session.createQuery("SELECT c.account FROM  Customer c where c.customerNumber = :customerNumber"
                        , Account.class)
                .setParameter("customerNumber", customerNumber)
                .uniqueResult();

    }

    @Override
    public Account updateAccount(Session session, Account account) {
        session.update(account);
        return account;
    }
}
