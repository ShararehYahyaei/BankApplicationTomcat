package entity;

public enum AccountType {
    SAVINGS(110),
    CURRENT(120),
    BUSINESS(130);

    private final int code;

    AccountType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
