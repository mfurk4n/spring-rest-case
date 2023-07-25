package com.javacase.javacase.service;

import com.javacase.javacase.dto.EmployeeDto;
import com.javacase.javacase.dto.request.EmployeeCreateRequest;
import com.javacase.javacase.dto.request.EmployeeUpdateRequest;
import com.javacase.javacase.exception.EmployeeNotFoundException;
import com.javacase.javacase.mapper.EmployeeDtoMapper;
import com.javacase.javacase.model.Company;
import com.javacase.javacase.model.Employee;
import com.javacase.javacase.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeDtoMapper employeeDtoMapper;
    private final CompanyService companyService;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeDtoMapper employeeDtoMapper, CompanyService companyService) {
        this.employeeRepository = employeeRepository;
        this.employeeDtoMapper = employeeDtoMapper;
        this.companyService = companyService;
    }

    public Employee getEmployeeById(String employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public EmployeeDto getEmployeeDtoById(String employeeId){
        Employee employee = getEmployeeById(employeeId);
        return employeeDtoMapper.convert(employee);
    }

    public List<EmployeeDto> getAllEmployeesDto(){
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeDtoMapper.convertEmployeeDtoList(employeeList);
    }

    public EmployeeDto createEmployee(EmployeeCreateRequest employeeCreateRequest){
        Company company = companyService.getCompanyById(employeeCreateRequest.getCompanyId());
        Employee employee = new Employee();
        employee.setName(employeeCreateRequest.getName());
        employee.setSurname(employeeCreateRequest.getSurname());
        employee.setTitle(employeeCreateRequest.getTitle());
        employee.setCreatedAt(LocalDateTime.now());
        employee.setCompany(company);

        return employeeDtoMapper.convert(employeeRepository.save(employee));
    }

    public EmployeeDto updateEmployeeById(String employeeId, EmployeeUpdateRequest employeeUpdateRequest) {
        Employee employee = getEmployeeById(employeeId);
        Company company = companyService.getCompanyById(employeeUpdateRequest.getCompanyId());
        employee.setTitle(employeeUpdateRequest.getTitle());
        employee.setCompany(company);
        return employeeDtoMapper.convert(employeeRepository.save(employee));
    }

    public void deleteEmployeeById(String employeeId){
        Employee employee = getEmployeeById(employeeId);
        employeeRepository.delete(employee);
    }


}
