package com.jpb.api.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jpb.api.dto.company.CompanyRequest;
import com.jpb.api.dto.company.CompanyResponse;
import com.jpb.api.dto.company.CompanyUpdateRequest;
import com.jpb.api.service.CompanyService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public CompanyResponse saveCompany(@Valid @RequestBody CompanyRequest request) {
        System.out.println("CompanyService.saveCompany()");
        return companyService.saveCompany(request);
    }

    @GetMapping
    public List<CompanyResponse> getAllCompanies() {
        System.out.println("CompanyService.getAllCompanies()");
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable Long id) {
        System.out.println("CompanyService.getCompanyById()");
        return companyService.getCompanyById(id);
    }

    @PutMapping("/{id}")
    public CompanyResponse updateCompany(@PathVariable Long id, @Valid @RequestBody CompanyUpdateRequest request) {
    	System.out.println("CompanyService.updateCompany()");
        return companyService.updateCompany(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable Long id) {
    	System.out.println("CompanyService.deleteCompany()");
        companyService.deleteCompany(id);
        return "Company deleted successfully";
    }
}
