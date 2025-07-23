package com.codelab.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "testcase")
public class TestCase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Input is required")
    @Size(max = 500, message = "Input must be less than 500 characters")
    private String input;

    @NotBlank(message = "Expected output is required")
    @Size(max = 500, message = "Expected output must be less than 500 characters")
    @Column(name = "expected_output")
    private String expectedOutput;

}
