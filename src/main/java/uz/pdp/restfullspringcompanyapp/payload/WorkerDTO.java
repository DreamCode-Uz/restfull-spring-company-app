package uz.pdp.restfullspringcompanyapp.payload;

import lombok.Data;

import java.util.Set;

@Data
public class WorkerDTO {
    private String name;
    private String phoneNumber;
    private String street;
    private Set<Integer> departmentsId;
}
