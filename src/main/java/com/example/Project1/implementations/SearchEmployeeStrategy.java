package com.example.Project1.implementations;

import com.example.Project1.models.Employee;
import com.example.Project1.models.GenericResponse;

import java.util.List;

public interface SearchEmployeeStrategy {

    GenericResponse<List<Employee>> execute(Long criteriaId, Iterable<Employee> allEmployees);
}
