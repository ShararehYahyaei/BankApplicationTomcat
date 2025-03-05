package service.accuntService;

import config.SessionFactoryInstance;
import dto.AccountDto;
import dto.TransferDto;
import entity.*;

import exception.accountException.AccountAlreadyExisted;
import exception.accountException.AccountNotFoundException;
import exception.customer.CustomerNotFound;
import org.hibernate.Session;
import repository.accountReposiotry.AccountRepository;
import repository.accountReposiotry.AccountRepositoryImpl;

import repository.customerRepository.CustomerRepoImpl;
import repository.customerRepository.CustomerRepository;
import repository.transactionRepository.TransactionRepository;
import repository.transactionRepository.TransactionRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountServiceInterface {
    private final AccountRepository accountRepository = new AccountRepositoryImpl();
    private final TransactionRepository transactionRepository = new TransactionRepositoryImpl();
    private final CustomerRepository customerRepository = new CustomerRepoImpl();


    @Override
    public Account save(AccountDto accountdto) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Account account = createAccountForExistedCustomer(accountdto);
                Customer byCustomerNumber = customerRepository.findByCustomerNumber(session, accountdto.getCustomerNumber());
                account.setCustomer(byCustomerNumber);
                if (isAccountNumberExisted(accountdto.getCustomerNumber())) {
                    throw new AccountAlreadyExisted("Account already existed");

                }
                Account saveAccount = accountRepository.save(session, account);
                byCustomerNumber.getAccounts().add(account);
                customerRepository.update(session, byCustomerNumber);
                session.getTransaction().commit();
                return saveAccount;
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();

            }
        }
        return null;
    }

    private static Account createAccountForExistedCustomer(AccountDto accountdto) {
        Account account = new Account();
        account.setAccountNumber(accountdto.getAccountNumber());
        account.setBalance(accountdto.getBalance());
        account.setAccountType(accountdto.getAccountType());
        return account;
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

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                Account accountByAccountNumber = accountRepository.getAccountByAccountNumber(session, accountNumber);
                if (accountByAccountNumber != null) {
                    session.getTransaction().commit();
                    return accountByAccountNumber;
                }
                throw new AccountNotFoundException("Account with account Number " + accountNumber + " does not exist");
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Account update(Account account) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Optional<Account> byId = accountRepository.findById(session, account.getId());
                if (byId.isPresent()) {
                    accountRepository.updateAccount(session, account);
                    session.getTransaction().commit();
                    return byId.get();
                }
            } catch (CustomerNotFound e) {
                e.getCause().printStackTrace();
                session.getTransaction().rollback();
                System.err.println("Error: " + e.getMessage());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;

    }

}