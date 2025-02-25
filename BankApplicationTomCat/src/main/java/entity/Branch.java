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
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    @OneToMany(mappedBy = "branch")
    List<Employee> employees = new ArrayList<>();
    @OneToMany
    List<Account> accounts = new ArrayList<>();
    @OneToOne
    private Manager manager;
    @OneToMany
    private List<Customer>customers = new ArrayList<>();
}
