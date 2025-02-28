package com.employee.payrollappdevelopment.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.util.List;
import java.time.LocalDate;

//Employee DTO class
@Data // Generate getters, setters method
public class EmployeeDTO {

    //Section:-05 Using MySQL Repository to store employee payroll data
    //UC-01 Add remaining properties to the payroll DTO and Model

    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z ]{2,}$", message = "Name must start with a capital letter and have at least 3 characters")
    private String name;

    @Min(value = 500, message = "Minimum wage should be more than 500")
    private double salary;
    private String gender;

    private LocalDate startDate;

    private String note;

    private String profilePic;

    private List<String> department;

    //  constructor
    public EmployeeDTO(String name, double salary, String gender, LocalDate startDate, String note, String profilePic, List<String> department) {
        this.name = name;
        this.salary = salary;
        this.gender = gender;
        this.startDate = startDate;
        this.note = note;
        this.profilePic = profilePic;
        this.department = department;
    }

}