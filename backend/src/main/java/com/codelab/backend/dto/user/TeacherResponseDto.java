package com.codelab.backend.dto.user;


import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
public class TeacherResponseDto extends UserResponseDto{
    private String username;
    private String department;
    private List<String> subjects;
}
