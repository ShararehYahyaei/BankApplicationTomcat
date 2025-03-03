package exception.customer;

public class CustomerPassword extends RuntimeException {
    public CustomerPassword(String message) {
        super(message);
    }
}
