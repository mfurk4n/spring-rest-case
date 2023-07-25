package com.javacase.javacase.service;

import com.javacase.javacase.dto.CompanyDto;
import com.javacase.javacase.dto.request.CompanyCreateRequest;
import com.javacase.javacase.dto.request.CompanyUpdateRequest;
import com.javacase.javacase.exception.CompanyNameAlreadyExistsException;
import com.javacase.javacase.exception.CompanyNotFoundException;
import com.javacase.javacase.mapper.CompanyDtoMapper;
import com.javacase.javacase.model.Company;
import com.javacase.javacase.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyDtoMapper companyDtoMapper;

    public CompanyService(CompanyRepository companyRepository, CompanyDtoMapper companyDtoMapper) {
        this.companyRepository = companyRepository;
        this.companyDtoMapper = companyDtoMapper;
    }

    public Company getCompanyById(String companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(CompanyNotFoundException::new);
    }

    public CompanyDto getCompanyDtoById(String companyId){
        Company company = getCompanyById(companyId);
        return companyDtoMapper.convert(company);
    }

    public List<CompanyDto> getAllCompaniesDto(){
        List<Company> companyList = companyRepository.findAll();
        return companyDtoMapper.convertCompanyDtoList(companyList);
    }

    public CompanyDto createCompany(CompanyCreateRequest companyCreateRequest){
        boolean companyNameCheck = companyRepository.existsByName(companyCreateRequest.getName());
        if(companyNameCheck)
            throw new CompanyNameAlreadyExistsException();
        Company company = new Company();
        company.setName(companyCreateRequest.getName());
        company.setAddress(companyCreateRequest.getAddress());
        company.setPhoneNumber(companyCreateRequest.getPhoneNumber());
        company.setCreatedAt(LocalDateTime.now());
        return companyDtoMapper.convert(companyRepository.save(company));
    }


    public CompanyDto updateCompanyById(String companyId, CompanyUpdateRequest companyUpdateRequest) {
        Company company = getCompanyById(companyId);
        company.setAddress(companyUpdateRequest.getAddress());
        company.setPhoneNumber(companyUpdateRequest.getPhoneNumber());
        return companyDtoMapper.convert(companyRepository.save(company));
    }

    public void deleteCompanyById(String companyId){
        Company company = getCompanyById(companyId);
        companyRepository.delete(company);
    }
}
