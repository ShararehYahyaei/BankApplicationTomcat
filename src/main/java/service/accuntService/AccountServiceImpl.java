package service.accuntService;

import config.SessionFactoryInstance;
import dto.TransferDto;
import entity.*;

import exception.accountException.AccountNotFoundException;
import repository.accountReposiotry.AccountRepository;
import repository.accountReposiotry.AccountRepositoryImpl;

import repository.transactionRepository.TransactionRepository;
import repository.transactionRepository.TransactionRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountServiceInterface {
    private final AccountRepository accountRepository = new AccountRepositoryImpl();
    private final TransactionRepository transactionRepository = new TransactionRepositoryImpl();


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
    public List<Account> getAccountsByCustomerNumber(String customerNumber) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            session.beginTransaction();
            List<Account> accounts = accountRepository.getAccountsByCustomerNumber(session, customerNumber);
            if (accounts.isEmpty()) {
                session.getTransaction().rollback();
                throw new AccountNotFoundException("Accounts not found");
            }
            session.getTransaction().commit();
            return accounts;
        }
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
    public void transfer(TransferDto transferDto) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                Account accountSource = accountRepository.getAccountByCardNumber(session, transferDto.getCardNumberSource());
                Account accountDestination = accountRepository.getAccountByCardNumber(session, transferDto.getCardNumberDestination());

                accountSource.setBalance(accountSource.getBalance() - transferDto.getAmount() - 600);
                accountDestination.setBalance(accountDestination.getBalance() + transferDto.getAmount());

                Transaction newTransaction = getTransaction(transferDto, accountSource, accountDestination);


                accountRepository.updateAccount(session, accountSource);
                accountRepository.updateAccount(session, accountDestination);
                transactionRepository.save(session, newTransaction);
                session.getTransaction().commit();

            } catch (Exception e) {
                e.printStackTrace();
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                session.getTransaction().rollback();
                System.out.println(e.getMessage());
            }

        }
    }

    private static Transaction getTransaction(TransferDto transferDto, Account accountSource, Account accountDestination) {
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transferDto.getAmount());
        newTransaction.setSource(accountSource);
        newTransaction.setDestination(accountDestination);
        newTransaction.setType(TransactionType.Transfer);
        newTransaction.setTransactionDate(LocalDate.now());
        return newTransaction;
    }

    @Override
    public boolean isAccountNumberExisted(String accountNumber) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                boolean accountNumberExisted = accountRepository.isAccountNumberExisted(session, accountNumber);
                if (accountNumberExisted) {
                    session.getTransaction().commit();
                    return true;
                }
            } catch (Exception e) {
                session.getTransaction().rollback();

            }
        }
        return false;
    }
}