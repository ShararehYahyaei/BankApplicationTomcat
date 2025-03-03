package exception.customer;

public class CustomerNumberNotBlank extends RuntimeException {
    public CustomerNumberNotBlank(String message) {
        super(message);
    }
}
