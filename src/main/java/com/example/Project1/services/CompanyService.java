package com.example.Project1.services;

import com.example.Project1.mappers.CompanyMapper;
import com.example.Project1.models.Company;
import com.example.Project1.models.CompanyResponse;
import com.example.Project1.models.Error;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CompanyService {

    private CompanyMapper mapper;

    private CompanyRepository repository;

    public CompanyService(CompanyMapper mapper, CompanyRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public GenericResponse getAllCompanies(){
        try {
            Iterable<Company> retrievedCompanies = repository.findAll();
            List<CompanyResponse> companies = new ArrayList<>();
            for (Company company : retrievedCompanies
            ) {
                companies.add(mapper.mapCompanyToCompanyResponse(company));
            }
            if (companies.isEmpty()) {
                return new GenericResponse<>(new Error(0, "Error", "Nothing found"));

            }
            return new GenericResponse<>(companies);
        }catch (Exception e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Internal Server Error", "Unable to retrieve data"));
        }


    }

    public GenericResponse<Company> getCompanyById(long id){
        try {
            Optional<Company> fetchedCompany = repository.findById(id);
            Company company= fetchedCompany.get();
            return new GenericResponse<>(company);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Wrong id for Company","Id : "+id+" does not exist" ));
        }
    }


}
