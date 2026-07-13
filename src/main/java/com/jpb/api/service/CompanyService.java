package com.jpb.api.service;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jpb.api.dao.CompanyRepository;
import com.jpb.api.dto.company.CompanyRequest;
import com.jpb.api.dto.company.CompanyResponse;
import com.jpb.api.dto.company.CompanyUpdateRequest;
import com.jpb.api.entity.Company;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    private CompanyResponse convertToResponse(Company company) {

        return CompanyResponse.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .phoneNumber(company.getPhoneNumber())
                .website(company.getWebsite())
                .industry(company.getIndustry())
                .location(company.getLocation())
                .description(company.getDescription())
                .logoUrl(company.getLogoUrl())
                .active(company.getActive())
                .createdAt(company.getCreatedAt())
                .updatedAt(company.getUpdatedAt())
                .userId(company.getUser().getId())
                .email(company.getUser().getEmail())
                .build();
    }
    
    public CompanyResponse saveCompany(CompanyRequest request) {
        System.out.println("CompanyService.saveCompany()");
        Company company=Company.builder()
        		    .companyName(request.getCompanyName())
                .phoneNumber(request.getPhoneNumber())
                .website(request.getWebsite())
                .industry(request.getIndustry())
                .location(request.getLocation())
                .description(request.getDescription())
                .logoUrl(request.getLogoUrl())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .active(true)
                .build();
        company= companyRepository.save(company);
        
        return convertToResponse(company);
    }

    public List<CompanyResponse> getAllCompanies() {
        System.out.println("CompanyService.getAllCompanies()");
        return companyRepository.findAll()
        		    .stream()
 	            .map(this::convertToResponse)
 	            .toList(); 		
    }

    public CompanyResponse getCompanyById(Long id) {
        System.out.println("CompanyService.getCompanyById()");
        Company company= companyRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Company not found"));
        return convertToResponse(company);
    }

    public CompanyResponse updateCompany(Long id,CompanyUpdateRequest request) {
        System.out.println("CompanyService.updateCompany()");
        Company company = companyRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Company not found"));

        company.setCompanyName(request.getCompanyName());
        company.setPhoneNumber(request.getPhoneNumber());
        company.setWebsite(request.getWebsite());
        company.setIndustry(request.getIndustry());
        company.setLocation(request.getLocation());
        company.setDescription(request.getDescription());
        company.setLogoUrl(request.getLogoUrl());
        company.setUpdatedAt(LocalDateTime.now());

        company= companyRepository.save(company);
        
        return convertToResponse(company);
    }

    public void deleteCompany(Long id) {
        System.out.println("CompanyService.deleteCompany()");
        Company company = companyRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Company not found"));
        company.setActive(false);
        company.setUpdatedAt(LocalDateTime.now());
        companyRepository.save(company);
    }
}