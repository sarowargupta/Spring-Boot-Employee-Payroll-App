package com.employee.payrollappdevelopment.controller;
import com.employee.payrollappdevelopment.dto.EmployeeDTO;
import com.employee.payrollappdevelopment.service.IEmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/employees")
@Validated
public class EmployeeController {

    //Section:-05 Using MySQL Repository to store employee payroll data
    //UC-02 Ensure validation is done on the payroll DTO

    @Autowired
    private IEmployeeService employeeService;


    //get all employees
    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        log.info("Fetching all employees");
        return employeeService.getAllEmployees();
    }

    //get employee by id
    @GetMapping("/{id}")
    public Optional<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        log.info("Fetching employee with ID: {}", id);
        return employeeService.getEmployeeById(id);
    }

    //add a new employee
    @PostMapping
    public EmployeeDTO createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        log.info("Received request to create employee: {}", employeeDTO);
        return employeeService.createEmployee(employeeDTO);
    }

    //update employee by id
    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        log.info("Updating employee with ID: {}", id);
        return employeeService.updateEmployee(id, employeeDTO);
    }

    //delete employee by id
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        log.info("Deleting employee with ID: {}", id);
        employeeService.deleteEmployee(id);
        return "Employee with ID " + id + " deleted successfully!";
    }
}