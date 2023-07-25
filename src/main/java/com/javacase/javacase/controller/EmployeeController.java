package com.javacase.javacase.controller;

import com.javacase.javacase.dto.EmployeeDto;
import com.javacase.javacase.dto.request.EmployeeCreateRequest;
import com.javacase.javacase.dto.request.EmployeeUpdateRequest;
import com.javacase.javacase.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployeesDto();
    }

    @PostMapping
    public EmployeeDto createEmployee(@Valid @RequestBody EmployeeCreateRequest employeeCreateRequest) {
        return employeeService.createEmployee(employeeCreateRequest);
    }

    @GetMapping("/{employeeId}")
    public EmployeeDto getOneEmployee(@PathVariable String employeeId) {
        return employeeService.getEmployeeDtoById(employeeId);
    }

    @PutMapping("/{employeeId}")
    public EmployeeDto updateEmployee(@PathVariable String employeeId,@Valid @RequestBody EmployeeUpdateRequest employeeUpdateRequest) {
        return employeeService.updateEmployeeById(employeeId, employeeUpdateRequest);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployeeById(employeeId);
    }
}
