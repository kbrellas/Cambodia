package com.example.Project1.implementations;

import com.example.Project1.models.Employee;

import java.util.List;

public interface SearchEmployeeStrategy {

    List<Employee> execute(Long criteriaId, Iterable<Employee> allEmployees);
}
