package com.codelab.backend.dto.user;


import lombok.experimental.SuperBuilder;

@SuperBuilder
public class AdminResponseDto extends UserResponseDto{
    private String username;
}
