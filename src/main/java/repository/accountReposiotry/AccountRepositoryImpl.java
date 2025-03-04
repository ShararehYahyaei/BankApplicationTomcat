package repository.accountReposiotry;

import entity.Account;
import entity.Customer;
import org.eclipse.tags.shaded.org.apache.bcel.generic.RET;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
    public Account updateAccount(Session session, Account account) {
        session.merge(account);
        session.flush();
        session.getTransaction().commit();
        return account;
    }

    @Override
    public List<Account> getAccountsByCustomerNumber(Session session, String customerNumber) {
        List<Account> accounts = session.createQuery("SELECT a FROM Account a JOIN a.customer c WHERE c.customerNumber = :customerNumber", Account.class)
                .setParameter("customerNumber", customerNumber).getResultList();
        return accounts;
    }

    @Override
    public Account getAccountByCardNumber(Session session, String cardNumber) {
        return session.createQuery("SELECT c.account FROM Card c WHERE c.cardNumber = :cardNumber", Account.class)
                .setParameter("cardNumber", cardNumber).getSingleResult();
    }

    @Override
    public Account getAccountByAccountNumber(Session session, String accountNumber) {
        return session.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber", Account.class)
                .setParameter("accountNumber", accountNumber).getSingleResult();

    }


}
