package com.student.firstproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {
    @NotBlank(message = "name cannot not be empty")
    private String name;
    @NotNull(message = "phone number cannot be null")
    @Pattern(regexp = "^[789]\\d{9}$", message = "Invalid phone number format")
    @Column(unique = true)
    private String phone;
}
