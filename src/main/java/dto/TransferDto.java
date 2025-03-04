package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class TransferDto {
    private String cardNumberSource;
    private String cardNumberDestination;
    private Long amount;
    private String cvv2;
    private LocalDate expiryDate;
    private String password;
    private String customerNumber;
    public Set<String> validate() {
        Set<String> validationErrors = new HashSet<>();


        if (cardNumberSource == null || cardNumberSource.length() != 16) {
            validationErrors.add("Source card number must be 16 digits.");
        }

        if (cardNumberDestination == null || cardNumberDestination.length() != 16) {
            validationErrors.add("Destination card number must be 16 digits.");
        }

        if (amount == null || amount <= 0) {
            validationErrors.add("Amount must be greater than zero.");
        }


        if (cvv2 == null || cvv2.length() != 3) {
            validationErrors.add("CVV2 must be 3 digits.");
        }


        if (expiryDate == null || expiryDate.isBefore(LocalDate.now())) {
            validationErrors.add("Expiry date must be in the future or present.");
        }


        if (password == null || password.length() < 6) {
            validationErrors.add("Password must be at least 6 characters.");
        }


        if (customerNumber == null) {
            validationErrors.add("Customer number cannot be null.");
        }

        return validationErrors;
    }
}
