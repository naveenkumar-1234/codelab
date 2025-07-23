package com.codelab.backend.dto.register;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentRegisterDTO extends UserRegisterDTO {

    @NotBlank
    private String username;

    @NotNull
    private Integer sprNumber;

    @NotBlank
    private String department;

    @NotNull
    private Integer academicYear;
}