package com.employee.payrollappdevelopment.service;
import com.employee.payrollappdevelopment.dto.EmployeeDTO;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    private final List<EmployeeDTO> employeeList = new ArrayList<>();

    //get all employee
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeList;
    }

    //get employee by id
    @Override
    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeList.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();
    }

    //create a new employee
    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        employeeDTO.setId((long) (employeeList.size() + 1)); // Generate a unique ID
        employeeList.add(employeeDTO);
        return employeeDTO;
    }

    //update employee by id
    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<EmployeeDTO> existingEmployee = getEmployeeById(id);
        if (existingEmployee.isPresent()) {
            EmployeeDTO updatedEmployee = existingEmployee.get();
            updatedEmployee.setName(employeeDTO.getName());
            updatedEmployee.setSalary(employeeDTO.getSalary());
            return updatedEmployee;
        } else {
            throw new RuntimeException("Employee not found with ID: " + id);
        }
    }

    //delete employee by id
    @Override
    public void deleteEmployee(Long id) {
        employeeList.removeIf(employee -> employee.getId().equals(id));
    }

}
