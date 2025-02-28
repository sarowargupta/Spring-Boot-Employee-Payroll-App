package com.employee.payrollappdevelopment.validation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // Global exception handler
public class GlobalExceptionHandler {

    // Handle exception if employee not found
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFound(EmployeeNotFoundException ex) {
        return new ResponseEntity<>("Employee Not Found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handle all other global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
