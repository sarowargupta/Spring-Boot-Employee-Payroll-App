package com.employee.payrollappdevelopment.service;
import com.employee.payrollappdevelopment.dto.EmployeeDTO;
import com.employee.payrollappdevelopment.validation.EmployeeNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    //Section:-04 & UC-02 Provide user-friendly error response in case validation fails

    private final List<EmployeeDTO> employeeList = new ArrayList<>();
    private long idCounter = 1L;

    //  Get all employees
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeList;
    }

    //  Get employee by ID (Throw exception if not found)
    @Override
    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeList.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .or(() -> { throw new EmployeeNotFoundException("Employee with ID " + id + " not found"); });
    }
    //  Create a new employee
    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        employeeDTO.setId(idCounter++);  // Auto-increment ID
        employeeList.add(employeeDTO);
        return employeeDTO;
    }

    //  Update an employee by id (Throw exception if not found)
    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        EmployeeDTO existingEmployee = getEmployeeById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));

        existingEmployee.setName(employeeDTO.getName());
        existingEmployee.setSalary(employeeDTO.getSalary());
        return existingEmployee;
    }


    // Delete an employee by id (Throw exception if not found)
    @Override
    public void deleteEmployee(Long id) {
        EmployeeDTO employee = getEmployeeById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
        employeeList.remove(employee);
    }

}
