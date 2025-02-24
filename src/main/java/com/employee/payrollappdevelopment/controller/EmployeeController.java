package com.employee.payrollappdevelopment.controller;
import com.employee.payrollappdevelopment.dto.EmployeeDTO;
import com.employee.payrollappdevelopment.model.Employee;
import com.employee.payrollappdevelopment.repository.EmployeeRepository;
import com.employee.payrollappdevelopment.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    //Section:01 Employee Payroll App Setup

    //UC-01 Employee Payroll Spring Project to cater to REST Request from Employee Payroll UI

    //UC-02 Rest Controller to demonstrate the various HTTP Methods

    @Autowired
    private EmployeeRepository employeeRepository;

    // Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // Add new employee
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // Update employee
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setName(employeeDetails.getName());
        employee.setSalary(employeeDetails.getSalary());
        return employeeRepository.save(employee);
    }

    // Delete employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return "Employee deleted successfully";
    }

    //Section:-02 Handling Employee Payroll DTO and Model In Employee Payroll Service Layer

    //UC-01 Introducing DTO and Model to Employee Payroll App

    // Convert Model to DTO
    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(employee.getName(), employee.getSalary());
    }

    // Get all employees (return DTOs)
    @GetMapping("/dto")
    public List<EmployeeDTO> getEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get employee by ID (return DTO)
    @GetMapping("/dto/{id}")
    public EmployeeDTO getEmployeesById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return convertToDTO(employee);
    }

    // Add new employee (accept DTO)
    @PostMapping("/dto")
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());

        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    // Update employee (accept DTO)
    @PutMapping("/dto/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(employeeDTO.getName());
                    employee.setSalary(employeeDTO.getSalary());
                    return convertToDTO(employeeRepository.save(employee));
                })
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // Delete employee
    @DeleteMapping("/dto/{id}")
    public void deleteEmployees(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }


    //UC-02 Introducing Service Layer in Employee Payroll App

    @Autowired
    private IEmployeeService employeeService;

    // Get all employees
    @GetMapping("/service")
    public List<EmployeeDTO> getAllsEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get employee by ID
    @GetMapping("/service/{id}")
    public Optional<EmployeeDTO> getEmployeeByIds(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    // Add new employee
    @PostMapping("/service")
    public EmployeeDTO createEmployees(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    // Update employee
    @PutMapping("/service/{id}")
    public EmployeeDTO updateEmployees(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(id, employeeDTO);
    }

    // Delete employee
    @DeleteMapping("/service/{id}")
    public void deletesEmployees(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

}
