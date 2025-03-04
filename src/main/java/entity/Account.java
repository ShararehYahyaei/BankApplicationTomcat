package entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @ManyToOne
    private Branch branch;
    @OneToOne(cascade = CascadeType.ALL)
    private Card card;
    private boolean isActive;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @ManyToMany (cascade = CascadeType.ALL)
    private List<Transaction> transactions=new ArrayList<>();

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
