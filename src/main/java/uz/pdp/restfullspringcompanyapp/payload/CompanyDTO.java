package uz.pdp.restfullspringcompanyapp.payload;

import lombok.Data;

@Data
public class CompanyDTO {
    private String name;
    private String director;
    private String street;
    private String homeNumber;
}
