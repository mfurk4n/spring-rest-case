package com.javacase.javacase.exception;

public class CompanyNameAlreadyExistsException extends RuntimeException{
    public CompanyNameAlreadyExistsException(){
        super("Company Name Already Exist!");
    }
}
