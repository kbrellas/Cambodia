package com.example.Project1.models;

public class EmployeeResponse {
    private long id;
    private int recordNumber;
    private String fullName;
    private String telephone;
    private String workingPeriod;
    private String status;
    private String contractType;
    private String position;
    private String unitName;

    public EmployeeResponse(long id, int recordNumber, String fullName, String telephone, String workingPeriod, String status, String contractType, String position, String unitName) {
        this.id = id;
        this.recordNumber = recordNumber;
        this.fullName = fullName;
        this.telephone = telephone;
        this.workingPeriod = workingPeriod;
        this.status = status;
        this.contractType = contractType;
        this.position = position;
        this.unitName = unitName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWorkingPeriod() {
        return workingPeriod;
    }

    public void setWorkingPeriod(String workingPeriod) {
        this.workingPeriod = workingPeriod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
