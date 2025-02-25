package service.accuntService;

import config.SessionFactoryInstance;
import entity.Account;
import entity.Customer;
import entity.Employee;
import repository.accountReposiotry.AccountRepository;
import repository.accountReposiotry.AccountRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountServiceInterface {
    private final AccountRepository accountRepository = new AccountRepositoryImpl();


    @Override
    public Account save(Account account) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Account saveAccount = accountRepository.save(session, account);
                session.getTransaction().commit();
                return saveAccount;

            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
        }
        return null;
    }

    @Override
    public Optional<Account> findById(Long id) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Optional<Account> byId = accountRepository.findById(session, id);
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
    public List<Account> findAll() {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                List<Account> accounts = accountRepository.findAll(session);
                session.getTransaction().commit();
                return accounts;
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();

            }
        }
        return null;
    }

    @Override
    public Customer update(Account account) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Optional<Account> found = accountRepository.findById(session, account.getId());
                if (found.isPresent()) {
                    found.get().setBalance(account.getBalance());
                    found.get().setActive(account.isActive());
                    accountRepository.save(session, found.get());
                } else {
                    System.out.println("Account not found");
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
    public void delete(Account account) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                new Employee().getId();
                Optional<Account> found = accountRepository.findById(session, account.getId());

                if (found.isPresent()) {
                    accountRepository.delete(session, account);
                    session.getTransaction().commit();
                } else {
                    System.out.println("Account with id " + account.getId() + " does not exist");
                    session.getTransaction().rollback();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
