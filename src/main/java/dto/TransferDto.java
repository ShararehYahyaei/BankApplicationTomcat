package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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
}
