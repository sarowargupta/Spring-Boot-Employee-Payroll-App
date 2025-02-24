package com.employee.payrollappdevelopment.repository;


import com.employee.payrollappdevelopment.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

