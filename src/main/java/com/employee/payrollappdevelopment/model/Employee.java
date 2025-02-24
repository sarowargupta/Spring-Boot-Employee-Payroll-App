package com.employee.payrollappdevelopment.model;
import jakarta.persistence.*;

//Section -02 :- UC-01 Introducing Model to Employee Payroll App

//Section -01:- UC-01 & 02

@Entity
@Table(name = "employees")

//class employee
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double salary;

    public Employee() {}

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    //getter and setter
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
    }
}
