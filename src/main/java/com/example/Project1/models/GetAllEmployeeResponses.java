package com.example.Project1.models;

import com.example.Project1.models.EmployeeResponse;

import java.util.List;

public class GetAllEmployeeResponses {
    private List<EmployeeResponse> employees;

    public GetAllEmployeeResponses(List<EmployeeResponse> employees) {
        this.employees = employees;
    }

    public List<EmployeeResponse> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeResponse> employees) {
        this.employees = employees;
    }
}
