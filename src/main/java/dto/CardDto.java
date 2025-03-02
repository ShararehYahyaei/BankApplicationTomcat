package dto;

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

}
