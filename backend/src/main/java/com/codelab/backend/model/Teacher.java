package com.codelab.backend.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@Data
@Table(name = "teacher")
@Builder
public class Teacher implements Serializable {

    @Id
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String department;

    @NotNull
    private Integer sprNumber;

    @ManyToOne
    @JoinColumn(name = "")
    private Subject subject;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

}
