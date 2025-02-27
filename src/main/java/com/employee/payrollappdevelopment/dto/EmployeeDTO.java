package com.employee.payrollappdevelopment.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

//Employee DTO class
@Data // Generate getters, setters method
public class EmployeeDTO {

    //Section:-04 & UC-02 Provide user-friendly error response in case validation fails

    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z ]{2,}$", message = "Name must start with a capital letter and have at least 3 characters")
    private String name;
    private double salary;

}