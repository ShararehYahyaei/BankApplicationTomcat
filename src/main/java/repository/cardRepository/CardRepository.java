package repository.cardRepository;

import entity.Card;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CardRepository {
    Card save(Session session, Card card);

    Optional<Card> findById(Session session, Long id);

    List<Card> findAll(Session session);

    void delete(Session session, Card card);

    LocalDate getCardExpirationDate(Session session, String cardNumber);

    Card findByCardNumber(Session session, String cardNumber);
    Card updateCard(Session session, Card card);

}
