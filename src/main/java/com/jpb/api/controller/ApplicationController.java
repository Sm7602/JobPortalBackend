package com.jpb.api.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jpb.api.entity.Application;
import com.jpb.api.service.ApplicationService;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public Application createApplication(@RequestParam Long candidateId,@RequestParam Long jobId, @RequestParam String coverLetter) {
        System.out.println("ApplicationController.createApplication()");
        return applicationService.createApplication(candidateId,jobId,coverLetter);
    }

    @GetMapping
    public List<Application> getAllApplications() {
        System.out.println("ApplicationController.getAllApplications()");
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public Application getApplicationById(@PathVariable Long id) {
        System.out.println("ApplicationController.getAllApplications()");
        return applicationService.getApplicationById(id);
    }

    @GetMapping("/job/{jobId}")
    public List<Application> getApplicationsByJob( @PathVariable Long jobId) {
        System.out.println("ApplicationController.getApplicationsByJob()");
        return applicationService.getApplicationsByJob(jobId);
    }

    @GetMapping("/candidate/{candidateId}")
    public List<Application> getApplicationsByCandidate(@PathVariable Long candidateId) {
        System.out.println("ApplicationController.getApplicationsByCandidate()");
        return applicationService.getApplicationsByCandidate(candidateId);
    }

    @PutMapping("/{id}")
    public Application updateApplication(@PathVariable Long id,@RequestParam String status,@RequestParam String coverLetter) {
    	  System.out.println("ApplicationController.updateApplication()");
        return applicationService.updateApplication(id,status,coverLetter);
    }

    @DeleteMapping("/{id}")
    public String deleteApplication(@PathVariable Long id) {
    	System.out.println("ApplicationController.deleteApplication()");
        applicationService.deleteApplication(id);
        return "Application deleted successfully";
    }
}
