package service.cardService;

import config.SessionFactoryInstance;
import dto.CardDto;
import entity.Account;
import entity.Card;
import entity.Customer;
import entity.Employee;
import exception.accountException.AccountHasACArd;
import exception.accountException.AccountIsNotActive;
import exception.cardException.CardNotFoundException;
import repository.accountReposiotry.AccountRepository;
import repository.accountReposiotry.AccountRepositoryImpl;
import repository.cardRepository.CardRepository;
import repository.cardRepository.CardRepositoryImpl;
import repository.customerRepository.CustomerRepoImpl;
import repository.customerRepository.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class CardService implements CardServiceInterface {
    private final CardRepository cardRepository = new CardRepositoryImpl();
    private final AccountRepository accountService = new AccountRepositoryImpl();
    private final CustomerRepository customerService = new CustomerRepoImpl();


    @Override
    public Card save(CardDto card) {
            try (var session = SessionFactoryInstance.getSessionFactory().openSession()) {
                session.beginTransaction();

                try {
                    Card newCard = getCreateCard(card);
                    String customerNumber = card.getCustomerNumber();
                    Customer byCustomerNumber = customerService.findByCustomerNumber(session, customerNumber);
                    Account accountByAccountNumber = accountService.getAccountByAccountNumber(session, card.getAccountNumber());

                    if (accountByAccountNumber.getCard() == null) {
                        if (accountByAccountNumber.isActive()) {
                            newCard.setAccount(accountByAccountNumber);
                            cardRepository.save(session, newCard);
                            byCustomerNumber.getCards().add(newCard);
                            customerService.update(session, byCustomerNumber);
                            accountByAccountNumber.setCard(newCard);
                            accountService.updateAccount(session, accountByAccountNumber);

                            session.getTransaction().commit();
                        } else {
                            throw new AccountIsNotActive("Account is not active");
                        }
                    } else {
                        throw new AccountHasACArd("This account has an active card");
                    }

                } catch (Exception e) {
                    e.printStackTrace();

                    if (session.getTransaction() != null && session.getTransaction().isActive()) {
                        session.getTransaction().rollback();
                    }
                    throw e;
                }
            } catch (Exception e) {

                e.printStackTrace();
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
                }
                throw new CardNotFoundException("Card not foud");
            } catch (CardNotFoundException e) {
                session.getTransaction().rollback();
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();

            }
        }

    }
}
