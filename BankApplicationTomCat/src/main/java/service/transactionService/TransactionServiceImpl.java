package service.transactionService;


import config.SessionFactoryInstance;
import entity.Employee;
import entity.Transaction;
import repository.transactionRepository.TransactionRepository;
import repository.transactionRepository.TransactionRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class TransactionServiceImpl implements TransactionServiceInter {
    private final TransactionRepository transactionRepository = new TransactionRepositoryImpl();

    @Override
    public Transaction save(Transaction transaction) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Transaction saveTransaction = transactionRepository.save(session, transaction);
                session.getTransaction().commit();
                return saveTransaction;

            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
        }
        return null;
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Optional<Transaction> byId = transactionRepository.findById(session, id);
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
    public List<Transaction> findAll() {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                List<Transaction> transactions = transactionRepository.findAll(session);
                session.getTransaction().commit();
                return transactions;
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();

            }
        }
        return null;
    }

    @Override
    public void delete(Transaction transaction) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                new Employee().getId();
                Optional<Transaction> found = transactionRepository.findById(session, transaction.getId());

                if (found.isPresent()) {
                    transactionRepository.delete(session, transaction);
                    session.getTransaction().commit();
                } else {
                    System.out.println("Transaction with id " + transaction.getId() + " does not exist");
                    session.getTransaction().rollback();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
