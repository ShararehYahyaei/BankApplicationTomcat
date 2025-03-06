package repository.cardRepository;

import entity.Card;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CardRepositoryImpl implements CardRepository {

    @Override
    public Card save(Session session, Card card) {
        session.persist(card);
        return card;
    }

    @Override
    public Optional<Card> findById(Session session, Long id) {
        Optional<Card> card = session.byId(Card.class).loadOptional(id);
        return card;
    }

    @Override
    public List<Card> findAll(Session session) {
        return session.createQuery("from Card").list();
    }

    @Override
    public void delete(Session session, Card card) {
        session.delete(card);
    }

    @Override
    public LocalDate getCardExpirationDate(Session session, String cardNumber) {
        return session.createQuery(
                        "SELECT c.expiryDate FROM Card c WHERE c.cardNumber = :cardNumber", LocalDate.class)
                .setParameter("cardNumber", cardNumber)
                .uniqueResult();
    }

    @Override
    public Card findByCardNumber(Session session, String cardNumber) {
        return session.createQuery("SELECT c FROM Card c WHERE c.cardNumber = :cardNumber", Card.class)
                .setParameter("cardNumber", cardNumber)
                .uniqueResult();
    }

    @Override
    public Card updateCard(Session session, Card card) {
        session.merge(card);
        session.flush();
        return card;
    }

    @Override
    public List<String> fetchCardByUserName(Session session, String userName) {

       return session.createQuery("SELECT c.cardNumber FROM Card c " +
               "JOIN Account a ON c.account.id = a.id " +
               "JOIN Customer cu ON a.customer.id = cu.id " +
               "WHERE cu.userName = :userName", String.class)
                .setParameter("userName", userName)
                .list();

    }

}
