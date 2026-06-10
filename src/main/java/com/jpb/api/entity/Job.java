package com.jpb.api.entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private Boolean active;

    private String title;

    @Column(length = 5000)
    private String description;

    private BigDecimal salary;

    private String location;

    private Integer vacancies;

    private Integer experienceRequired;

    private String skillsRequired;

    private String jobType;

    private LocalDate applicationDeadline;
    
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    
    @OneToMany(mappedBy = "job")
    private List<Application> applications;
}
