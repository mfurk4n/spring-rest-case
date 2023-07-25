package com.javacase.javacase.mapper;

import com.javacase.javacase.dto.CompanyDto;
import com.javacase.javacase.model.Company;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyDtoMapper {
    private final EmployeeDtoMapper employeeDtoMapper;

    public CompanyDtoMapper(EmployeeDtoMapper employeeDtoMapper) {
        this.employeeDtoMapper = employeeDtoMapper;
    }

    public CompanyDto convert(Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .address(company.getAddress())
                .phoneNumber(company.getPhoneNumber())
                .employees(employeeDtoMapper.convertEmployeeDtoList(new ArrayList<>(company.getEmployees())))
                .createdAt(company.getCreatedAt())
                .build();
    }

    public List<CompanyDto> convertCompanyDtoList(List<Company> companyList) {
        return companyList
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
