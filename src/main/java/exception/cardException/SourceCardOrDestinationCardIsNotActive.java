package exception.cardException;

public class SourceCardOrDestinationCardIsNotActive extends RuntimeException {
    public SourceCardOrDestinationCardIsNotActive(String message) {
        super(message);
    }
}
