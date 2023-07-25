package com.javacase.javacase.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyUpdateRequest {
    @NotBlank
    @Size(max = 255)
    String address;

    @NotBlank
    @Size(max = 15)
    String phoneNumber;
}
