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

@Slf4j
@Service
public class EmployeeService implements IEmployeeService {

    //Section:-05 Using MySQL Repository to store employee payroll data
    //UC-02 Ensure validation is done on the payroll DTO

    @Autowired
    private EmployeeRepository repository;

    //get all employees
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.info("Fetching all employees from database");
        return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    //get employee by its id
    @Override
    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id);
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
        return Optional.of(convertToDTO(employee));
    }

    //add a new employee
    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        log.info("Creating a new employee: {}", employeeDTO);
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = repository.save(employee);
        return convertToDTO(savedEmployee);
    }

    //update employee by id
    @Override
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

    //delete employee by id
    @Override
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with ID: {}", id);
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
        repository.delete(employee);
    }

    //convert DTO
    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(employee.getName(), employee.getSalary(), employee.getGender(),
                employee.getStartDate(), employee.getNote(), employee.getProfilePic(), employee.getDepartment());
    }

    //convert to entity
    private Employee convertToEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setSalary(dto.getSalary());
        employee.setGender(dto.getGender());
        employee.setStartDate(dto.getStartDate());
        employee.setNote(dto.getNote());
        employee.setProfilePic(dto.getProfilePic());
        employee.setDepartment(dto.getDepartment());
        return employee;
    }
}
