package com.employee.payrollappdevelopment.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
@Data // Generate getters, setters method
@NoArgsConstructor
@AllArgsConstructor
//class employee
public class Employee {

    //Section:-05 Using MySQL Repository to store employee payroll data
    //UC-01 Add remaining properties to the payroll DTO and Model

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private Double salary;
    private String gender;
    private LocalDate startDate;
    private String note;
    private String profilePic;

    @ElementCollection
    private List<String> department;



 /*   //getter and setter
    public Long getId() {

        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    } */

}