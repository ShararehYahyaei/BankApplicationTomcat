package dto;

import entity.AccountType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
public class AccountDto {
    private String customerNumber;
    private AccountType accountType;
    private Long balance;
    private String accountNumber;

    public Set<String> validate() {
        Set<String> errors = new HashSet<>();
        if (accountNumber == null || !accountNumber.matches("\\d{10,20}")) {
            errors.add("Account number must be between 10 and 20 digits.");
        }
        if (balance == null || balance < 0) {
            errors.add("Account balance cannot be negative.");

        }
        if (customerNumber == null || customerNumber.isEmpty()) {
            errors.add("Customer number cannot be empty.");
        }
        return errors;
    }

}
