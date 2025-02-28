package com.employee.payrollappdevelopment.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
@Data // Generate getters, setters method
@NoArgsConstructor
@ToString
//class employee
public class Employee {

    //Section:-05 Using MySQL Repository to store employee payroll data
    //UC-04 ability to save employee payroll data to mysql DB

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double salary;

    @Column(nullable = false)
    private String gender;

    @JsonFormat(pattern = "dd MMM yyyy")
    @NotNull(message = "Start date should not be empty")
    @PastOrPresent(message = "Start date should be past or today's date")
    private LocalDate startDate;

    @Column(nullable = false)
    private String note;

    @Column(nullable = false)
    private String profilePic;

    @ElementCollection
    private List<String> department;

    // Constructor convertToEntity method
    public Employee(String name, double salary, String gender, LocalDate startDate,
                    String note, String profilePic, List<String> department) {
        this.name = name;
        this.salary = salary;
        this.gender = gender;
        this.startDate = startDate;
        this.note = note;
        this.profilePic = profilePic;
        this.department = department;
    }


}