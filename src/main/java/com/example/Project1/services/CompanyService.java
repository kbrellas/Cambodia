package com.example.Project1.services;

import com.example.Project1.mappers.CompanyMapper;
import com.example.Project1.models.Company;
import com.example.Project1.models.CompanyResponse;
import com.example.Project1.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyMapper mapper;

    @Autowired
    private CompanyRepository repository;

    public List<CompanyResponse> getAllCompanies(){
        Iterable<Company> retrievedCompanies= repository.findAll();
        List<CompanyResponse> companies= new ArrayList<>();
        for (Company company : retrievedCompanies
                ) {
            companies.add(mapper.mapCompanyToCompanyResponse(company));
        }
        return companies;
    }


}
