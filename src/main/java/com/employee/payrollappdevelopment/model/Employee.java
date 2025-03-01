package com.employee.payrollappdevelopment.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "employees")
@Data//Generate getters,setters method
@ToString
//class employee
public class Employee {

    //Section:-05 Using MySQL Repository to store employee payroll data
    //UC-06 Ability to retrieve all the records of employee payroll pertaining to sales department

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    private String name;
    private double salary;
    private String gender;

    @JsonFormat(pattern = "dd MMM yyyy")
    private LocalDate startDate;

    private String note;
    private String profilePic;

    @ElementCollection
    @CollectionTable(name = "employee_department", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "department")
    private List<String> department;


    // constructor
    public Employee(String name, double salary, String gender, LocalDate startDate, String note, String profilePic, List<String> department) {
        this.name = name;
        this.salary = salary;
        this.gender = gender;
        this.startDate = startDate;
        this.note = note;
        this.profilePic = profilePic;
        this.department = department;
    }
}