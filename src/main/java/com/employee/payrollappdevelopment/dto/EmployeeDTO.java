package com.employee.payrollappdevelopment.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.time.LocalDate;

//Employee DTO class
@Data // Generate getters, setters method
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    //Section:-05 Using MySQL Repository to store employee payroll data
    //UC-05 CRUD Service Methods with MySQL Database

    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z ]{2,}$", message = "Name must start with a capital letter and have at least 3 characters")
    private String name;

    @Min(value = 500, message = "Minimum wage should be more than 500")
    private double salary;

    @Pattern(regexp = "male|female", message = "Gender needs to be male or female")
    private String gender;

    @JsonFormat(pattern = "dd MMM yyyy")
    @NotNull(message = "Start Date should not be empty")
    @PastOrPresent(message = "Start Date should be past or today's date")
    private LocalDate startDate;

    @NotBlank(message = "Note cannot be blank")
    private String note;

    @NotBlank(message = "Profile picture URL cannot be blank")
    private String profilePic;

    @NotEmpty(message = "Department cannot be empty")
    private List<String> department;
}