package uz.pdp.restfullspringcompanyapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CompanyDTO {

    @NotNull(message = "Company name should not be null")
    @Size(message = "Company name must be at least 2 characters.", min = 2)
    private String name;

    @NotNull(message = "Director name should not be null")
    @Size(message = "Director name must be at least 2 characters.")
    private String director;

    @NotNull(message = "Street name should not be null")
    private String street;
    private String homeNumber;
}
