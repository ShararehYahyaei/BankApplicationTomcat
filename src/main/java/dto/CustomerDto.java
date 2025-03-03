package dto;

import entity.AccountType;
import exception.accountException.AccountNumberException;
import exception.accountException.NegativeBalanceException;
import exception.customer.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
    private String fullName;
    private String lastName;
    private String email;
    private String phone;
    private AccountType accountType;
    private Long balance;
    private String accountNumber;
    private String code;
    private String userName;
    private String password;
    private String customerNumber;
    public void validate() {
        if (fullName == null || fullName.length() < 2 || fullName.length() > 50) {
            throw new NameException("Full name must be between 2 and 50 characters.");
        }

        if (lastName == null || lastName.length() < 2 || lastName.length() > 50) {
            throw new LastNameException("Last name must be between 2 and 50 characters.");
        }

        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new EmailException("اInvalid email address.");
        }

        if (phone == null || !phone.matches("^\\+98\\d{10,12}$")) {
            throw new PhoneException("Phone number must be between 10 and 12 digits and start with +98.");
        }

        if (accountNumber == null || !accountNumber.matches("\\d{10,20}")) {
            throw new AccountNumberException("Account number must be between 10 and 20 digits.");
        }

        if (password == null || password.length() < 6 || password.length() > 20) {
            throw new CustomerPassword("Password must be between 6 and 20 characters.");
        }

        if (balance == null || balance < 0) {
            throw new NegativeBalanceException("Account balance cannot be negative.");
        }

        if (customerNumber == null || customerNumber.isEmpty()) {
            throw new CustomerNumberNotBlank("Customer number cannot be empty.");
        }
    }



}
