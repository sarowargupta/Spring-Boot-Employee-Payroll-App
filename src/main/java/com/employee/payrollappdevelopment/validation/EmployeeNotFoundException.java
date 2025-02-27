package com.employee.payrollappdevelopment.validation;

//handle exception when employee not found
public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}