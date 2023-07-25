package com.javacase.javacase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javacase.javacase.model.Employee;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    String id;
    String name;
    String address;
    String phoneNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt;
    List<EmployeeDto> employees;
}
