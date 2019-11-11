package com.example.Project1.mappers;

import com.example.Project1.models.Company;
import com.example.Project1.models.CompanyResponse;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyResponse mapCompanyToCompanyResponse(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName()
        );
    }

}
