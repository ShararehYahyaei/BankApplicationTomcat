package service.accuntService;

import config.SessionFactoryInstance;
import dto.TransferDto;
import entity.Account;
import entity.Card;
import entity.Customer;
import entity.Employee;
import exception.accountException.AccountNotFoundException;
import exception.accountException.CardIsNotActiveException;
import exception.accountException.InsufficientBalanceException;
import exception.cardException.CardIsExpired;
import exception.cardException.CardNotFoundException;
import repository.accountReposiotry.AccountRepository;
import repository.accountReposiotry.AccountRepositoryImpl;
import repository.cardRepository.CardRepository;
import repository.cardRepository.CardRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountServiceInterface {
    private final AccountRepository accountRepository = new AccountRepositoryImpl();
    private final CardRepository cardRepository = new CardRepositoryImpl();


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
    public Account update(Account account) {
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

    @Override
    public Account getAccountByCustomerNumber(String customerNumber) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                return accountRepository.getAccountByCustomerNumber(session, customerNumber);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public Account updateAccount(Account account) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                accountRepository.updateAccount(session, account);
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();

            }
            return account;
        }
    }


    public void withdraw(TransferDto transferDto) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {
            session.beginTransaction();

            try {

                Card card = cardRepository.getCardExpirationDate(session, transferDto.getCustomerNumber());
                Account account = accountRepository.getAccountByCustomerNumber(session, transferDto.getCustomerNumber());

                if (account == null) {
                    throw new AccountNotFoundException("حسابی با این شماره مشتری یافت نشد!");
                }
                if (card == null) {
                    throw new CardNotFoundException("کارت مربوط به این حساب یافت نشد!");
                }


                if (!account.isActive()) {
                    throw new CardIsNotActiveException("حساب غیرفعال است!");
                }


                if (!card.getExpiryDate().isAfter(LocalDate.now())) {
                    throw new CardIsExpired("کارت منقضی شده است!");
                }


                if (account.getBalance() < transferDto.getAmount()) {
                    throw new InsufficientBalanceException("موجودی حساب کافی نیست!");
                }

                account.setBalance(account.getBalance() - transferDto.getAmount());
                accountRepository.updateAccount(session, account);
                session.getTransaction().commit();

            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException("خطا در عملیات برداشت وجه: " + e.getMessage(), e);
            }
        }
    }

}
