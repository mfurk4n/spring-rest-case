package com.javacase.javacase.repository;

import com.javacase.javacase.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,String> {
    boolean existsByName(String name);
}
