package service.transactionService;

import entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionServiceInter {
    Transaction save(Transaction transaction);
    Optional<Transaction> findById(Long id);
    List<Transaction> findAll();
    void delete(Transaction transaction);
}
