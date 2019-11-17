package com.example.Project1.implementations;

import com.example.Project1.models.*;
import com.example.Project1.models.Error;
import com.example.Project1.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public class SearchEmployeeByUnitStrategy implements SearchEmployeeStrategy {

    private UnitService service;

    public SearchEmployeeByUnitStrategy(UnitService service) {
        this.service = service;
    }

    @Override
    public GenericResponse<List<Employee>> execute(Long criteriaId, Iterable<Employee> allEmployees) {

        GenericResponse<Unit> idResponse= service.getUnitById(criteriaId);
        if (idResponse.getError()!= null){
            return new GenericResponse<>(idResponse.getError());
        }

        List<Employee> employees = new ArrayList<>();
        for (Employee employee : allEmployees
        ) {
            if (employee.getUnit().getId() == criteriaId) {
                employees.add(employee);
            }
        }
        if(employees.isEmpty())
            return new GenericResponse<>(new Error(0,"No Employees Found","No Employees found for Unit"));
        return new GenericResponse<>(employees);
    }
}
