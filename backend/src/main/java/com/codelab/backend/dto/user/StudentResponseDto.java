    package com.codelab.backend.dto.user;

    import com.codelab.backend.model.Subject;
    import lombok.Data;
    import lombok.Getter;
    import lombok.experimental.SuperBuilder;

    import java.util.List;


    @SuperBuilder
    @Data
    public class StudentResponseDto extends UserResponseDto {
        private String username;
        private Integer sprNumber;
        private String department;
        private Integer academicYear;
        private List<Subject> subject;
    }
