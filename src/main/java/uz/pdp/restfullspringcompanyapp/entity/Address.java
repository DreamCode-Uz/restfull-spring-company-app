package uz.pdp.restfullspringcompanyapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String street;

    @Column(name = "home_number")
    private String homeNumber;

    public Address(String street, String homeNumber) {
        this.street = street;
        this.homeNumber = homeNumber;
    }
}
