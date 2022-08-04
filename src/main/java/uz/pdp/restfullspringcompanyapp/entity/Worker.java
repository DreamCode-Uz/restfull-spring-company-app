package uz.pdp.restfullspringcompanyapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Worker name should not be null.")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Phone number should not be null.")
    @Column(nullable = false,unique = true, name = "phone_number")
    private String phoneNumber;

    @OneToOne(optional = false)
    private Address address;

    @ManyToMany
    private Set<Department> department;

    public Worker(String name, String phoneNumber, Address address, Set<Department> department) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.department = department;
    }
}
