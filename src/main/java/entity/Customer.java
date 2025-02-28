package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String lastName;
    private String email;
    private String phone;
    private String userName;
    private String password;
    private String customerNumber;
    @OneToOne
    private Account account;
    @ManyToOne
    private Branch branch;


    public Customer(String fullName,String lastName, String email, String phone) {
        this.fullName = fullName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}
