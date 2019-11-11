package com.example.Project1.models;

import java.util.List;

public class GetAllDepartmentResponses {
    private List<DepartmentResponse> departments;

    public GetAllDepartmentResponses(List<DepartmentResponse> departments) {
        this.departments = departments;
    }

    public List<DepartmentResponse> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentResponse> departments) {
        this.departments = departments;
    }
}
