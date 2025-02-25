package com.employee.payrollappdevelopment.service;
import com.employee.payrollappdevelopment.dto.EmployeeDTO;
import com.employee.payrollappdevelopment.model.Employee;
import com.employee.payrollappdevelopment.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

//Section:-02 UC-02 Introducing Service Layer in Employee Payroll App

@Slf4j //enables SLF4J logging
@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Convert Model to DTO
    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(employee.getName(), employee.getSalary());
    }

    // Convert DTO to Model
    private Employee convertToModel(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        //Section-02 & UC-03
        employee.setId(idCounter.getAndIncrement()); // Simulate auto-increment ID
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        return employee;
    }

//    @Override
//    public List<EmployeeDTO> getAllEmployees() {
//        return employeeRepository.findAll()
//                .stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }

//    @Override
//    public Optional<EmployeeDTO> getEmployeeById(Long id) {
//        return employeeRepository.findById(id).map(this::convertToDTO);
//    }

//    @Override
//    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
//        Employee savedEmployee = employeeRepository.save(convertToModel(employeeDTO));
//        return convertToDTO(savedEmployee);
//    }

//    @Override
//    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
//        return employeeRepository.findById(id)
//                .map(employee -> {
//                    employee.setName(employeeDTO.getName());
//                    employee.setSalary(employeeDTO.getSalary());
//                    return convertToDTO(employeeRepository.save(employee));
//                })
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//    }

//    @Override
//    public void deleteEmployee(Long id) {
//        employeeRepository.deleteById(id);
//    }

    //Section:-02 & UC-03 Ability for the service layer to store the employee payroll data

//    private final List<Employee> employeeList = new ArrayList<>();
//    private final AtomicLong idCounter = new AtomicLong(1); // To simulate auto-increment ID



    //Section:-03 Application Setting & UC-02 Lombok library for logging


    private final List<Employee> employeeList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1); // Simulate auto-increment ID

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.info("Fetching all employees");
        return employeeList.stream()
                .map(emp -> new EmployeeDTO(emp.getName(), emp.getSalary()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        log.debug("Fetching employee with ID: {}", id);
        return employeeList.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .map(emp -> new EmployeeDTO(emp.getName(), emp.getSalary()));
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(idCounter.getAndIncrement());
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employeeList.add(employee);
        log.info("Created new employee: {}", employee);
        return new EmployeeDTO(employee.getName(), employee.getSalary());
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        log.warn("Updating employee with ID: {}", id);
        Optional<Employee> existingEmployee = employeeList.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst();

        if (existingEmployee.isPresent()) {
            existingEmployee.get().setName(employeeDTO.getName());
            existingEmployee.get().setSalary(employeeDTO.getSalary());
            return new EmployeeDTO(existingEmployee.get().getName(), existingEmployee.get().getSalary());
        } else {
            log.error("Employee with ID {} not found", id);
            throw new RuntimeException("Employee not found");
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with ID: {}", id);
        employeeList.removeIf(emp -> emp.getId().equals(id));
    }

}
