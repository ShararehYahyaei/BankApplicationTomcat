package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private String cvv2;
    private LocalDate expiryDate;
    private String password;
    @OneToOne (cascade = CascadeType.ALL)
    private Account account;
    private int failedAttempts;
    private boolean isBlocked;

    public Card(String cardNumber, String cvv2, LocalDate expiryDate, String password, String customerNumber) {
        this.cardNumber = cardNumber;
        this.cvv2 = cvv2;
        this.expiryDate = expiryDate;
        this.password = password;
        this.account = new Account();

    }
}
