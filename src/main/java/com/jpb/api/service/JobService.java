package com.jpb.api.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jpb.api.dao.CompanyRepository;
import com.jpb.api.dao.JobRepository;
import com.jpb.api.dto.job.JobRequest;
import com.jpb.api.dto.job.JobResponse;
import com.jpb.api.dto.job.JobUpdateRequest;
import com.jpb.api.entity.Company;
import com.jpb.api.entity.Job;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;
    
    private JobResponse convertToResponse(Job job) {

        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .salary(job.getSalary())
                .location(job.getLocation())
                .vacancies(job.getVacancies())
                .experienceRequired(job.getExperienceRequired())
                .skillsRequired(job.getSkillsRequired())
                .jobType(job.getJobType())
                .applicationDeadline(job.getApplicationDeadline())
                .active(job.getActive())
                .createdAt(job.getCreatedAt())
                .updatedAt(job.getUpdatedAt())
                .companyId(job.getCompany().getId())
                .companyName(job.getCompany().getCompanyName())
                .build();
    }

    public JobResponse saveJob(Long companyId,JobRequest request) {
        System.out.println("JobService.saveJob()");
        Company company = companyRepository.findById(companyId).orElseThrow(() ->
                        new RuntimeException("Company not found"));

        Job job= Job.builder()
        		    .title(request.getTitle())
                .description(request.getDescription())
                .salary(request.getSalary())
                .location(request.getLocation())
                .vacancies(request.getVacancies())
                .experienceRequired(request.getExperienceRequired())
                .skillsRequired(request.getSkillsRequired())
                .jobType(request.getJobType())
                .applicationDeadline(request.getApplicationDeadline())
                .company(company)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .active(true)
                .build();
        job=jobRepository.save(job);
        
        return convertToResponse(job);
    }

    public List<JobResponse> getAllJobs() {
    	System.out.println("JobService.getAllJobs() ");
        return jobRepository.findAll()
        		    .stream()
 	            .map(this::convertToResponse)
 	            .toList();
    }

    public JobResponse getJobById(Long id) {
    	System.out.println("JobService.getJobById()");
        Job job= jobRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Job not found"));
        return convertToResponse(job);
    }

    public JobResponse updateJob(Long id, JobUpdateRequest request) {
        System.out.println("JobService.updateJob()");
        Job job = jobRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Job not found"));

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setSalary(request.getSalary());
        job.setLocation(request.getLocation());
        job.setVacancies(request.getVacancies());
        job.setExperienceRequired(request.getExperienceRequired());
        job.setSkillsRequired(request.getSkillsRequired());
        job.setJobType(request.getJobType());
        job.setApplicationDeadline(request.getApplicationDeadline());
        job.setUpdatedAt(LocalDateTime.now());

        job=jobRepository.save(job);
        
        return convertToResponse(job);
    }
    
    public void deleteJob(Long id) {
    	System.out.println("JobService.deleteJob()");
        Job job = jobRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Job not found"));
        job.setActive(false);
        job.setUpdatedAt(LocalDateTime.now());
        jobRepository.save(job);
    }

    public List<JobResponse> searchJobs(String keyword) {
    	System.out.println("JobService.searchJobs()");
        return jobRepository.findByTitleContainingIgnoreCase(keyword)
        		    .stream()
 	            .map(this::convertToResponse)
 	            .toList();
    }

    public List<JobResponse> getJobsByLocation(String location) {
    	System.out.println("JobService.getJobsByLocation()");
        return jobRepository.findByLocationIgnoreCase(location)
        		    .stream()
 	            .map(this::convertToResponse)
 	            .toList();
    }

    public List<JobResponse> getJobsByCompany(Long companyId) {
    	System.out.println("JobService.getJobsByCompany()");
        Company company = companyRepository.findById(companyId).orElseThrow(() ->
                        new RuntimeException("Company not found"));
        return jobRepository.findByCompany(company)
        		    .stream()
 	            .map(this::convertToResponse)
 	            .toList();
    }
}
