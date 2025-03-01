package com.employee.payrollappdevelopment.service;
import com.employee.payrollappdevelopment.dto.EmployeeDTO;
import com.employee.payrollappdevelopment.model.Employee;
import com.employee.payrollappdevelopment.repository.EmployeeRepository;
import com.employee.payrollappdevelopment.validation.EmployeeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService implements IEmployeeService {

    //Section:-05 Using MySQL Repository to store employee payroll data
    //UC-06 Ability to retrieve all the records of employee payroll pertaining to sales department

    @Autowired
    private EmployeeRepository repository;

    @Override
    //get all employees
    public List<EmployeeDTO> getAllEmployees() {
        log.info("Fetching all employees");
        return ((List<Employee>) repository.findAll())
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    //get employee by id
    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id);
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
        return Optional.of(convertToDTO(employee));
    }

    @Override
    //create a new employee
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        log.info("Creating new employee: {}", employeeDTO.getName());
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = repository.save(employee);
        return convertToDTO(savedEmployee);
    }

    @Override
    // Update employee by ID
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        log.info("Updating employee with ID: {}", id);
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

    @Override
    // Delete employee by ID
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with ID: {}", id);
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
        repository.delete(employee);
    }

    @Override
    // Get employees by department
    public List<EmployeeDTO> getEmployeesByDepartment(String department) {
        log.info("Fetching employees from department: {}", department);
        return repository.findEmployeesByDepartment(department)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Converts Employee entity to DTO
    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(employee.getId(),employee.getName(), employee.getSalary(), employee.getGender(),
                employee.getStartDate(), employee.getNote(), employee.getProfilePic(), employee.getDepartment());
    }

    // Converts EmployeeDTO to Entity
    private Employee convertToEntity(EmployeeDTO dto) {
        return new Employee(dto.getName(), dto.getSalary(), dto.getGender(), dto.getStartDate(),
                dto.getNote(), dto.getProfilePic(), dto.getDepartment());
    }
}