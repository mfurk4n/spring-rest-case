package com.javacase.javacase.exception;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(){
        super("Employee Not Found!");
    }
}
