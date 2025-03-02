package exception.cardException;

public class CardIsExpired extends RuntimeException {
    public CardIsExpired(String message) {
        super(message);
    }
}
