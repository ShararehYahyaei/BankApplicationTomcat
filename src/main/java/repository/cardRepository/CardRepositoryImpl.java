package repository.cardRepository;

import entity.Card;
import org.hibernate.Session;
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
}
