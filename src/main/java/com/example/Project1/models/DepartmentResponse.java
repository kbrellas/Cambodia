package com.example.Project1.models;

public class DepartmentResponse {
    private long id;
    private String name;
    private String businessUnitName;

    public DepartmentResponse(long id, String name, String businessUnitName) {
        this.id = id;
        this.name = name;
        this.businessUnitName = businessUnitName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessUnitName() {
        return businessUnitName;
    }

    public void setBusinessUnitName(String businessUnitName) {
        this.businessUnitName = businessUnitName;
    }
}
