package com.employee.payrollappdevelopment.controller;
import com.employee.payrollappdevelopment.dto.EmployeeDTO;
import com.employee.payrollappdevelopment.service.IEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/employees")
@Validated
public class EmployeeController {

    //Section:-04 & UC-01 Data Validation and Exception handling

    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //  Get All Employees
    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    //  Get Employee by ID
    @GetMapping("/{id}")
    public Optional<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    //create an employee
    @PostMapping
    public EmployeeDTO createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    //update employee by id
    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(id, employeeDTO);
    }

    // Delete an Employee by ID
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "Employee with ID " + id + " deleted successfully!";
    }
}
