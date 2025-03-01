package com.employee.payrollappdevelopment.repository;


import com.employee.payrollappdevelopment.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    //Section:-05 Using MySQL Repository to store employee payroll data
    //UC-06 Ability to retrieve all the records of employee payroll pertaining to sales department

    // Custom query to retrieve employees belonging to the "Sales" department
    @Query(value = "SELECT * FROM employee_payroll, where employee_id = id and department = :department",nativeQuery = true)
    List<Employee> findEmployeesByDepartment(String department);
}

