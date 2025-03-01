package com.employee.payrollappdevelopment.service;
import com.employee.payrollappdevelopment.dto.EmployeeDTO;
import com.employee.payrollappdevelopment.model.Employee;
import com.employee.payrollappdevelopment.repository.EmployeeRepository;
import com.employee.payrollappdevelopment.validation.EmployeeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeService implements IEmployeeService {

    //Section:-05 Using MySQL Repository to store employee payroll data
    //UC-05 CRUD Service Methods with MySQL Database

    @Autowired
    private EmployeeRepository repository;

    // Get all employees
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.info("Fetching all employees...");
        return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get employee by ID
    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
        return convertToDTO(employee);
    }

    // Create a new employee
    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        log.info("Creating new employee...");
        Employee savedEmployee = repository.save(convertToEntity(employeeDTO));
        return convertToDTO(savedEmployee);
    }

    // Update an employee
    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        log.info("Updating employee with ID: " + id);
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employee.setGender(employeeDTO.getGender());
        employee.setStartDate(employeeDTO.getStartDate());
        employee.setNote(employeeDTO.getNote());
        employee.setProfilePic(employeeDTO.getProfilePic());
        employee.setDepartment(employeeDTO.getDepartment());

        Employee updatedEmployee = repository.save(employee);
        return convertToDTO(updatedEmployee);
    }

    // Delete an employee
    @Override
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with ID: " + id);
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
        repository.delete(employee);
    }

    // Convert Employee to EmployeeDTO
    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(employee.getName(), employee.getSalary(), employee.getGender(),
                employee.getStartDate(), employee.getNote(), employee.getProfilePic(), employee.getDepartment());
    }

    // Convert EmployeeDTO to Employee
    private Employee convertToEntity(EmployeeDTO dto) {
        return new Employee(dto.getName(), dto.getSalary(), dto.getGender(), dto.getStartDate(),
                dto.getNote(), dto.getProfilePic(), dto.getDepartment());
    }
}
