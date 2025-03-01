package com.employee.payrollappdevelopment.service;
import com.employee.payrollappdevelopment.dto.EmployeeDTO;
import java.util.List;
import java.util.Optional;

//Section:-05 Using MySQL Repository to store employee payroll data
//UC-06 Ability to retrieve all the records of employee payroll pertaining to sales department

public interface IEmployeeService {
    List<EmployeeDTO> getAllEmployees();
    Optional<EmployeeDTO> getEmployeeById(Long id);
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);
    void deleteEmployee(Long id);
    List<EmployeeDTO> getEmployeesByDepartment(String department);
    
}