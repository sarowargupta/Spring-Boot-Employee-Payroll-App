package com.employee.payrollappdevelopment.service;
import com.employee.payrollappdevelopment.dto.EmployeeDTO;
import java.util.List;


//Section:-02 UC-02 Introducing Service Layer in Employee Payroll App
//Create the service interface

public interface IEmployeeService {
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO  getEmployeeById(Long id);
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);
    void deleteEmployee(Long id);
    
}