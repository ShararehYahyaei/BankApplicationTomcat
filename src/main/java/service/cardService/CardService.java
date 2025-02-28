package service.cardService;

import config.SessionFactoryInstance;
import entity.Account;
import entity.Card;
import entity.Employee;
import repository.cardRepository.CardRepository;
import repository.cardRepository.CardRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class CardService implements CardServiceInterface {
    private final CardRepository cardRepository=new CardRepositoryImpl();
    @Override
    public Card save(Card card) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Card saveCard = cardRepository.save(session, card);
                session.getTransaction().commit();
                return saveCard;

            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
        }
        return null;
    }

    @Override
    public Optional<Card> findById(Long id) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Optional<Card> byId = cardRepository.findById(session, id);
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
    public List<Card> findAll() {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                List<Card> cards = cardRepository.findAll(session);
                session.getTransaction().commit();
                return cards;
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();

            }
        }
        return null;
    }

    @Override
    public void delete(Card card) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                new Employee().getId();
                Optional<Card> found = cardRepository.findById(session, card.getId());
                if (found.isPresent()) {
                    cardRepository.delete(session, card);
                    session.getTransaction().commit();
                } else {
                    System.out.println("Card with id " + card.getId() + " does not exist");
                    session.getTransaction().rollback();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
