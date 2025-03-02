package exception.accountException;

public class CardIsNotActiveException extends RuntimeException {
    public CardIsNotActiveException(String message) {
        super(message);
    }
}
