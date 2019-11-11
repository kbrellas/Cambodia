package com.example.Project1.implementations;

import com.example.Project1.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class SearchEmployeeByCompanyStrategy implements SearchEmployeeStrategy {

    @Override
    public List<Employee> execute(Long criteriaId, Iterable<Employee> allEmployees) {
        List<Employee> employees =new ArrayList<>();
        for (Employee employee: allEmployees
        ) {
            if(employee.getCompany().getId()==criteriaId){
                employees.add(employee);
            }
        }
        return employees;
    }
}


