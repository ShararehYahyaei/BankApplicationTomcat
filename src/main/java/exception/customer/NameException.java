package exception.customer;

public class NameException extends RuntimeException {
    public NameException(String message) {
        super(message);
    }
}
