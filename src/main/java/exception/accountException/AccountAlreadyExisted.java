package exception.accountException;

public class AccountAlreadyExisted extends RuntimeException {
    public AccountAlreadyExisted(String message) {
        super(message);
    }
}
