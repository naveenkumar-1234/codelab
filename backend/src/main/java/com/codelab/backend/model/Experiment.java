package com.codelab.backend.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "experiment")
public class Experiment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Experiment number is required")
    @Min(value = 1 , message = "Experiment number must be at least 1")
    @Column(name = "experiment_no" , nullable = false)
    private Integer experimentNo;

    @NotBlank(message = "Experiment name is required")
    @Size(max = 100, message = "Experiment name must be less than 100 characters")
    @Column(name = "experiment_name", nullable = false)
    private String experimentName;

    @Size(max = 1000, message = "Description must be less than 1000 characters")
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id" , nullable = false)
    private Subject subject;

    @Size(max = 200, message = "Constraints must be less than 200 characters")
    private String constraints;

    @Valid
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "experiment_id")
    private List<TestCase> testCaseList = new ArrayList<>();

    @Column(name = "experiment_identifier" , unique = true)
    private String experimentIdentifier;

    @PrePersist
    private void generateIdentifier(){
        this.experimentIdentifier =
                this.subject.getId() + "-" + this.experimentNo;
    }

}
