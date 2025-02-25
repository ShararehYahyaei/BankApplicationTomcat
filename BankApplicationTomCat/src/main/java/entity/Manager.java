package entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {

    @Id
    private Long id;
    private String name;
    @OneToMany
    List<Employee> employees;
    @OneToOne
    private Branch branch;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
