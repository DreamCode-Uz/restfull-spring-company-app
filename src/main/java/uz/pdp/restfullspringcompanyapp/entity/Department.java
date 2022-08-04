package uz.pdp.restfullspringcompanyapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Department name is required.")
    @NotEmpty(message = "Section name must not be empty.")
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Company company;

    public Department(String name, Company company) {
        this.name = name;
        this.company = company;
    }
}
