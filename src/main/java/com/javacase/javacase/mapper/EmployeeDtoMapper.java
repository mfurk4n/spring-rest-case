package com.javacase.javacase.mapper;

import com.javacase.javacase.dto.EmployeeDto;
import com.javacase.javacase.model.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeDtoMapper {
    public EmployeeDto convert(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .title(employee.getTitle())
                .createdAt(employee.getCreatedAt())
                .companyId(employee.getCompany().getId())
                .companyName(employee.getCompany().getName())
                .build();
    }

    public List<EmployeeDto> convertEmployeeDtoList(List<Employee> employeeList) {
        return employeeList
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
