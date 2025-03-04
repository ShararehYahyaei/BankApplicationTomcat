package service.accuntService;

import config.SessionFactoryInstance;
import dto.TransferDto;
import entity.Account;
import entity.Card;
import entity.Transaction;
import entity.TransactionType;

import exception.accountException.AccountNotFoundException;
import repository.accountReposiotry.AccountRepository;
import repository.accountReposiotry.AccountRepositoryImpl;

import repository.cardRepository.CardRepository;
import repository.cardRepository.CardRepositoryImpl;
import repository.transactionRepository.TransactionRepository;
import repository.transactionRepository.TransactionRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountServiceInterface {
    private final AccountRepository accountRepository = new AccountRepositoryImpl();
    private final TransactionRepository transactionRepository = new TransactionRepositoryImpl();
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
                Card cardSource = cardRepository.findByCardNumber(session, transferDto.getCardNumberSource());
                Card cardDestination = cardRepository.findByCardNumber(session, transferDto.getCardNumberDestination());


                if (cardSource.isBlocked()) {
                    throw new RuntimeException("card source is blocked");
                }


                if (!cardSource.getPassword().equals(transferDto.getPassword())) {
                    cardSource.setFailedAttempts(cardSource.getFailedAttempts() + 1);


                    if (cardSource.getFailedAttempts() >= 3) {
                        cardSource.setBlocked(true);
                        throw new RuntimeException("card source is blocked");
                    }


                    cardRepository.save(session, cardSource);
                    throw new RuntimeException("pin is incorrect");
                }


                Account accountSource = accountRepository.getAccountByCardNumber(session, transferDto.getCardNumberSource());
                Account accountDestination = accountRepository.getAccountByCardNumber(session, transferDto.getCardNumberDestination());


                accountSource.setBalance(accountSource.getBalance() - transferDto.getAmount() - 600);
                accountDestination.setBalance(accountDestination.getBalance() + transferDto.getAmount());


                Transaction newTransaction = new Transaction();
                newTransaction.setAmount(transferDto.getAmount());
                newTransaction.setSource(accountSource);
                newTransaction.setDestination(accountDestination);
                newTransaction.setType(TransactionType.Transfer);
                newTransaction.setTransactionDate(LocalDate.now());


                if (!session.contains(accountSource)) {
                    accountRepository.updateAccount(session, accountSource);

                }
                if (!session.contains(accountDestination)) {
                    accountRepository.updateAccount(session, accountDestination);
                }


                transactionRepository.save(session, newTransaction);
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
                System.out.println(e.getMessage());
            }
        }
    }


}