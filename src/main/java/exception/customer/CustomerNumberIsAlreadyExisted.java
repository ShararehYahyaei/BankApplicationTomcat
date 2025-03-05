package exception.customer;

public class CustomerNumberIsAlreadyExisted extends RuntimeException {
    public CustomerNumberIsAlreadyExisted(String message) {
        super(message);
    }
}
