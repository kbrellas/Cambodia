package com.example.Project1.implementations;

import com.example.Project1.models.*;
import com.example.Project1.models.Error;
import com.example.Project1.services.CompanyService;

import java.util.ArrayList;
import java.util.List;

public class SearchEmployeeByCompanyStrategy implements SearchEmployeeStrategy {

    private CompanyService service;

    public SearchEmployeeByCompanyStrategy(CompanyService service) {
        this.service = service;
    }

    @Override
    public GenericResponse<List<Employee>> execute(Long criteriaId, Iterable<Employee> allEmployees) {

        GenericResponse<Company> idResponse= service.getCompanyById(criteriaId);
        if (idResponse.getError()!= null){
            return new GenericResponse<>(idResponse.getError());
        }
        List<Employee> employees =new ArrayList<>();
        for (Employee employee: allEmployees
        ) {
            if(employee.getCompany().getId()==criteriaId){
                employees.add(employee);
            }
        }
        if(employees.isEmpty())
            return new GenericResponse<>(new Error(0,"No Employees Found","No Employees found for Company"));
        return new GenericResponse<>(employees);
    }
}


