package uz.pdp.restfullspringcompanyapp.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DepartmentDTO {

    @NotBlank
    @NotNull(message = "Department name should not be null")
    private String name;

    @NotNull(message = "Company id should not be null")
    private Integer companyId;
}
