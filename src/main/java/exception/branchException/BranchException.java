package exception.branchException;

public class BranchException extends RuntimeException {
    public BranchException(String message) {
        super("Branch Not Found: " + message);
    }
}
