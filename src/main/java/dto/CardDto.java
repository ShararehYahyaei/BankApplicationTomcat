package dto;

import exception.cardException.CardCvvNumbers;
import exception.cardException.CardIsExpired;
import exception.cardException.CardNumbers;
import exception.cardException.CardPassword;
import exception.customer.CustomerNumberNotBlank;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
public class CardDto {
    private String cardNumber;
    private String cvv2;
    private LocalDate expiryDate;
    private String password;
    private String customerNumber;
    private String accountNumber;
    public void validate() {
        Set<String> errors = new HashSet<>();
        if (cardNumber == null || !cardNumber.matches("\\d{16}")) {
            errors.add("Card number must be 16 digits.");

        }

        if (cvv2 == null || !cvv2.matches("\\d{3}")) {
            errors.add("CVV2 must be 3 digits.");
        }

        if (expiryDate == null || expiryDate.isBefore(LocalDate.now())) {
            errors.add("Expiry date must be in the future.");

        }

        if (password == null || password.length() < 4 || password.length() > 6) {
            errors.add("Card password must be between 4 and 6 digits.");
        }

        if (customerNumber == null || customerNumber.isEmpty()) {
            errors.add("Customer number cannot be empty.");

        }
        if (accountNumber == null || accountNumber.isEmpty()) {
            errors.add("Account number must be between 10 and 20 digits..");
        }
    }

}
