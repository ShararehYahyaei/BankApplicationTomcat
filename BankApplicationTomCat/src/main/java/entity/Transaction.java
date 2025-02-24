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
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    private LocalDate transactionDate;
    private TransactionType type;
    @OneToOne(cascade = CascadeType.ALL)
    private Account destination;
    @OneToOne(cascade = CascadeType.ALL)
    private Account sourceAccount;

}
