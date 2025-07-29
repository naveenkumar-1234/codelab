package com.codelab.backend.dto.user;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class UserResponseDto {
    private Long id;
    private String email;
    private String role;
}
