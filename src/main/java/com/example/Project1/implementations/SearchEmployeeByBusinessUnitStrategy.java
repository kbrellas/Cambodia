package com.example.Project1.implementations;

import com.example.Project1.models.Employee;
import com.example.Project1.models.Error;
import com.example.Project1.models.GenericResponse;

import java.util.ArrayList;
import java.util.List;

public class SearchEmployeeByBusinessUnitStrategy implements SearchEmployeeStrategy {

    @Override
    public GenericResponse<List<Employee>> execute(Long criteriaId, Iterable<Employee> allEmployees) {
        List<Employee> employees =new ArrayList<>();
        for (Employee employee: allEmployees
        ) {
            if(employee.getBusinessUnit().getId()==criteriaId){
                employees.add(employee);
            }
        }
        if(employees.isEmpty())
            return new GenericResponse<>(new Error(0,"No Employees Found","No Employees found for Bussiness Unit"));
        return new GenericResponse<>(employees);
    }
}

