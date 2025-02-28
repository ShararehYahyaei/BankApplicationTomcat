package repository.cardRepository;

import entity.Card;
import entity.Transaction;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface CardRepository {
    Card save(Session session, Card card);

    Optional<Card> findById(Session session, Long id);

    List<Card> findAll(Session session);

    void delete(Session session, Card card);
}
