package com.javacase.javacase.exception;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException(){
        super("Company Not Found!");
    }
}
