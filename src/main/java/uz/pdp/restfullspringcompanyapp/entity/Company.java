package uz.pdp.restfullspringcompanyapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "The company name must not be null.")
    @NotEmpty(message = "The company name must not be empty.")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Director's name must be entered.")
    @Column(nullable = false)
    private String director;

    @OneToOne(optional = false)
    private Address address;

    public Company(String name, String director, Address address) {
        this.name = name;
        this.director = director;
        this.address = address;
    }
}
