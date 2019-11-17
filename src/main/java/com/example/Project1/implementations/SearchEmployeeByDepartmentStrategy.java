package com.example.Project1.implementations;

import com.example.Project1.models.*;
import com.example.Project1.models.Error;
import com.example.Project1.services.DepartmentService;

import java.util.ArrayList;
import java.util.List;

public class SearchEmployeeByDepartmentStrategy implements SearchEmployeeStrategy {

    private DepartmentService service;

    public SearchEmployeeByDepartmentStrategy(DepartmentService service) {
        this.service = service;
    }

    @Override
    public GenericResponse<List<Employee>> execute(Long criteriaId, Iterable<Employee> allEmployees) {

        GenericResponse<Department> idResponse = service.getDepartmentById(criteriaId);
        if (idResponse.getError()!= null){
            return new GenericResponse<>(idResponse.getError());
        }
        List<Employee> employees =new ArrayList<>();
        for (Employee employee: allEmployees
             ) {
            if(employee.getDepartment().getId()==criteriaId){
                employees.add(employee);
            }
        }
        if(employees.isEmpty())
            return new GenericResponse<>(new Error(0,"No Employees Found","No Employees found for Department"));
        return new GenericResponse<>(employees);
    }
}
