package exception.customer;

public class EmailException extends RuntimeException {
    public EmailException(String message) {
        super(message);
    }
}
