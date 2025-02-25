package com.employee.payrollappdevelopment.dto;

// Section:-02 UC -01 Introducing DTO class

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Section :-03 Application Setting & UC-01 lombok library to autogenerate
//getter and setter for the DTO

//Employee DTO class
@Data // Generate getters, setters method
@NoArgsConstructor // lombok generate no argument constructor
@AllArgsConstructor // Generate a constructor with argument of all field
public class EmployeeDTO {
    private String name;
    private double salary;

}