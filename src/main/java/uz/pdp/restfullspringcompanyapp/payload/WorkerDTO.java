package uz.pdp.restfullspringcompanyapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class WorkerDTO {

    @NotNull(message = "Company name should not be null")
    @Size(message = "Company name must be at least 2 characters.", min = 2)
    private String name;

    @NotNull(message = "Company name should not be null")
    @Pattern(regexp = "^\\+[1-9]\\d{1,14}$", message = "Please enter the phone number in the correct format (e.g: +919367788755)")  // +919367788755
    private String phoneNumber;

    @NotNull(message = "Street name should not be null")
    private String street;

    private String homeNumber;

    @NotNull(message = "Department(s) id must be entered")
    private Set<Integer> departmentsId;
}
