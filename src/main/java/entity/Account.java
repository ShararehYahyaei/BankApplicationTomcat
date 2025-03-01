package entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private Long balance;
    @OneToOne
    private Customer customer;
    @ManyToOne
    private Branch branch;
    @OneToOne
    private Card card;
    private boolean isActive;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;


    @Override
    public String toString() {
        return "Account {\n" +
                "  accountNumber='" + accountNumber + "',\n" +
                "  balance=" + balance + ",\n" +
                "  card=" + card + ",\n" +
                "  isActive=" + isActive + ",\n" +
                "  accountType=" + accountType + "\n" +
                "}";
    }
}
