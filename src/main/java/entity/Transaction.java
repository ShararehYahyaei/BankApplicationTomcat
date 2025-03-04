package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    private LocalDate transactionDate;
    private TransactionType type;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "destination_id")
    private Account destination;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "source_id")
    private Account source;

}
