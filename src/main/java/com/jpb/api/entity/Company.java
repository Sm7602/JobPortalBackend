package com.jpb.api.entity;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Company {

	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	
	    private LocalDateTime createdAt;

	    private LocalDateTime updatedAt;

	    private Boolean active;

	    private String companyName;

	    private String email;

	    private String phoneNumber;

	    private String website;

	    private String industry;

	    private String location;

	    private String description;

	    private String logoUrl;
    
       @OneToMany(mappedBy = "company")
       private List<Job> jobs;
}
