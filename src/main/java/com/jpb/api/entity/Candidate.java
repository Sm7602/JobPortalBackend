package com.jpb.api.entity;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Candidate {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private LocalDateTime createdAt;

	    private LocalDateTime updatedAt;

	    private Boolean active;
	    
	    private String firstName;

	    private String lastName;

	    private String email;

	    private String phoneNumber;

	    private String city;

	    private String skills;

	    private Integer experienceYears;

	    private String qualification;

	    private String resumeUrl;

	    private String linkedinUrl;

	    private String githubUrl;
	    
	    @OneToMany(mappedBy = "candidate")
	    private List<Application> applications;
	    
	    @OneToOne
	    @JoinColumn(name="user_id")
	    private User user;
	
}
