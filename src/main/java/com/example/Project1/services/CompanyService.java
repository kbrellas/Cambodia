package com.example.Project1.services;

import com.example.Project1.mappers.CompanyMapper;
import com.example.Project1.models.*;
import com.example.Project1.models.Error;
import com.example.Project1.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.swing.text.html.Option;
import java.lang.reflect.Field;
import java.util.*;

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


    public GenericResponse<CompanyResponse> createCompany(Company company) {
        repository.save(company);
        return new GenericResponse<>(mapper.mapCompanyToCompanyResponse(company));
    }

    public GenericResponse<CompanyResponse> updateCompany(long companyId, Company company) {
        Optional<Company> fetchedCompany = repository.findById(companyId);
        if(!fetchedCompany.isPresent()){
            return new GenericResponse<>(new Error(0,"Wrong Input","Company with id: "+companyId+" does not exist"));

        }
        Company retrievedCompany= fetchedCompany.get();
        Map<String, Object> companyMap = new HashMap<>();
        Field[] allFields = company.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            try {

                if(!field.getName().equalsIgnoreCase("id")) {
                    Object value = field.get(company);
                    if (value != null&& !value.equals(0)) {
                        companyMap.put(field.getName(), value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return new GenericResponse<>(new Error(0, "Cannot Access field", "Field : "+field));
            }
            field.setAccessible(false);
        }

        companyMap.forEach((k, v) -> {
            // use reflection to get field k on retrievedEmployee and set it to value k
            Field field = ReflectionUtils.findField(Company.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, retrievedCompany, v);
            field.setAccessible(false);
        });

        repository.save(retrievedCompany);
        return new GenericResponse<>(mapper.mapCompanyToCompanyResponse(retrievedCompany));
    }
}
