package service.accuntService;

import config.SessionFactoryInstance;
import dto.TransferDto;
import entity.Account;
import entity.Transaction;
import entity.TransactionType;

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
    private final TransactionRepository transactionService = new TransactionRepositoryImpl();


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
    public void withdraw(TransferDto transferDto) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {
            session.beginTransaction();
            Account accountSource = getAccountByCardNumber(transferDto.getCardNumberSource());
            Account accountDestination = getAccountByCardNumber(transferDto.getCardNumberDestination());
            accountSource.setBalance(accountSource.getBalance() - transferDto.getAmount());
            accountDestination.setBalance(accountDestination.getBalance() + transferDto.getAmount());
            Transaction newTransaction = getNewTransaction(transferDto);
            accountRepository.updateAccount(session, accountSource);
            accountRepository.updateAccount(session, accountDestination);
            transactionService.save(session, newTransaction);
            session.getTransaction().commit();

        }
    }

    @Override
    public Account getAccountByCardNumber(String cardNumber) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {
            session.beginTransaction();
            Account accountByCardNumber = accountRepository.getAccountByCardNumber(session, cardNumber);
            if (accountByCardNumber != null) {
                session.getTransaction().commit();
                return accountByCardNumber;
            }
            throw new AccountNotFoundException("Account not found");
        }
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        try(var session = SessionFactoryInstance.getSessionFactory().openSession()) {
            session.beginTransaction();
            Account accountByAccountNumber = accountRepository.getAccountByAccountNumber(session, accountNumber);
            if (accountByAccountNumber != null) {
                session.getTransaction().commit();
                return accountByAccountNumber;
            }
        }
        throw new AccountNotFoundException("Account not found");
    }

    private Transaction getNewTransaction(TransferDto transferDto) {
        Transaction tr = new Transaction();
        tr.setAmount(transferDto.getAmount());
        tr.setType(TransactionType.Transfer);
        tr.setTransactionDate(LocalDate.now());
        return tr;
    }

}