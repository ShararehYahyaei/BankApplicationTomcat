package service.cardService;

import dto.CardDto;
import entity.Card;

import java.util.List;
import java.util.Optional;

public interface CardServiceInterface {
    Card save(CardDto card);

    Optional<Card> findById(Long id);

    List<Card> findAll();

    void delete(Card card);

    Card UpdateCard(Card card);
    Card findByCardNumber(String cardNumber);
    List<String>fetchByUserName(String userName);
}
