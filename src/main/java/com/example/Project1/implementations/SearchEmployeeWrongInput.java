package com.example.Project1.implementations;

import com.example.Project1.models.Employee;
import com.example.Project1.models.Error;

import com.example.Project1.models.GenericResponse;

import java.util.List;

public class SearchEmployeeWrongInput implements SearchEmployeeStrategy {
    @Override
    public GenericResponse<List<Employee>> execute(Long criteriaId, Iterable<Employee> allEmployees) {
        return new GenericResponse<>(new Error (0, "Wrong criteria string input","String input should be : unit , department, businessUnit or company"));
    }
}
