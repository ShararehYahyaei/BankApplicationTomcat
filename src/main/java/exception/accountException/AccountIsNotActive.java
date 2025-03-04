package exception.accountException;

public class AccountIsNotActive extends RuntimeException {
    public AccountIsNotActive(String message) {
        super(message);
    }
}
