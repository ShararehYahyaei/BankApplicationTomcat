package repository.transactionRepository;

import entity.Transaction;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class TransactionRepositoryImpl implements TransactionRepository {
    @Override
    public Transaction save(Session session, Transaction transaction) {
        session.persist(transaction);
        return transaction;
    }

    @Override
    public Optional<Transaction> findById(Session session, Long id) {
        Optional<Transaction> transaction = session.byId(Transaction.class).loadOptional(id);
        return transaction;
    }

    @Override
    public List<Transaction> findAll(Session session) {
        return session.createQuery("from Transaction").list();
    }

    @Override
    public void delete(Session session, Transaction transaction) {
        session.delete(transaction);
    }
}
