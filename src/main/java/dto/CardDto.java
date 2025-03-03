package dto;

import exception.cardException.CardCvvNumbers;
import exception.cardException.CardIsExpired;
import exception.cardException.CardNumbers;
import exception.cardException.CardPassword;
import exception.customer.CustomerNumberNotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class CardDto {
    private String cardNumber;
    private String cvv2;
    private LocalDate expiryDate;
    private String password;
    private String customerNumber;
    public void validate() {
        if (cardNumber == null || !cardNumber.matches("\\d{16}")) {
            throw new CardNumbers("Card number must be 16 digits.");
        }

        if (cvv2 == null || !cvv2.matches("\\d{3}")) {
            throw new CardCvvNumbers("CVV2 must be 3 digits.");
        }

        if (expiryDate == null || expiryDate.isBefore(LocalDate.now())) {
            throw new CardIsExpired("Expiry date must be in the future.");
        }

        if (password == null || password.length() < 4 || password.length() > 6) {
            throw new CardPassword("Card password must be between 4 and 6 digits.");
        }

        if (customerNumber == null || customerNumber.isEmpty()) {
            throw new CustomerNumberNotBlank("Customer number cannot be empty.");
        }
    }

}
