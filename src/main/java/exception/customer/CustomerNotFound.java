package exception.customer;

public class CustomerNotFound extends RuntimeException {
    public CustomerNotFound(String message) {
        super( message);
    }


}
