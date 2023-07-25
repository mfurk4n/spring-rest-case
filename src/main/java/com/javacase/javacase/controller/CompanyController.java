package com.javacase.javacase.controller;

import com.javacase.javacase.dto.CompanyDto;
import com.javacase.javacase.dto.request.CompanyCreateRequest;
import com.javacase.javacase.dto.request.CompanyUpdateRequest;
import com.javacase.javacase.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyDto> getAllCompanies() {
        return companyService.getAllCompaniesDto();
    }

    @PostMapping
    public CompanyDto createCompany(@Valid @RequestBody CompanyCreateRequest companyCreateRequest) {
        return companyService.createCompany(companyCreateRequest);
    }

    @GetMapping("/{companyId}")
    public CompanyDto getOneCompany(@PathVariable String companyId) {
        return companyService.getCompanyDtoById(companyId);
    }

    @PutMapping("/{companyId}")
    public CompanyDto updateCompany(@PathVariable String companyId,@Valid @RequestBody CompanyUpdateRequest companyUpdateRequest) {
        return companyService.updateCompanyById(companyId, companyUpdateRequest);
    }

    @DeleteMapping("/{companyId}")
    public void deleteCompany(@PathVariable String companyId) {
        companyService.deleteCompanyById(companyId);
    }
}
