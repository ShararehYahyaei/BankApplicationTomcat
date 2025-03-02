package service.cardService;

import config.SessionFactoryInstance;
import dto.CardDto;
import entity.Account;
import entity.Card;
import entity.Employee;
import repository.accountReposiotry.AccountRepository;
import repository.accountReposiotry.AccountRepositoryImpl;
import repository.cardRepository.CardRepository;
import repository.cardRepository.CardRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class CardService implements CardServiceInterface {
    private final CardRepository cardRepository = new CardRepositoryImpl();
    private final AccountRepository accountService = new AccountRepositoryImpl();


    @Override
    public Card save(CardDto card) {
        try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {

            try {
                session.beginTransaction();
                Card newCard = getCreateCard(card);
                Account accountByCustomerNumber = accountService.getAccountByCustomerNumber(session, card.getCustomerNumber());
                accountByCustomerNumber.setCard(newCard);
                newCard.setAccount(accountByCustomerNumber);
                accountService.save(session, accountByCustomerNumber);
                accountService.save(session, accountByCustomerNumber);
                cardRepository.save(session, newCard);
                session.getTransaction().commit();
                return newCard;

            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
        }
        return null;
    }

    private static Card getCreateCard(CardDto card) {
        Card newCard = new Card();
        newCard.setCardNumber(card.getCardNumber());
        newCard.setPassword(card.getPassword());
        newCard.setCvv2(card.getCvv2());
        newCard.setExpiryDate(card.getExpiryDate());
        return newCard;
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
