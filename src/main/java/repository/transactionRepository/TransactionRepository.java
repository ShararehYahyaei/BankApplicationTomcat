package repository.transactionRepository;

import entity.Transaction;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Transaction save(Session session, Transaction transaction);

    Optional<Transaction> findById(Session session, Long id);

    List<Transaction> findAll(Session session);

    void delete(Session session, Transaction transaction);
    Transaction update(Session session, Transaction transaction);
}
