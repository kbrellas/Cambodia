package com.example.Project1.models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int recordNumber;
    private String lastName;
    private String firstName;
    private String homeAddress;
    private String telephoneNo;
    @NotNull
    private LocalDate hireDate;

    private LocalDate leaveDate;
    private Status status;

    private ContractType contractType;

    @ManyToOne
    private Company company;
    @ManyToOne
    private BusinessUnit businessUnit;
    @ManyToOne
    private Department department;
    @ManyToOne
    /*@JoinTable( name = "Unit_Employee",
            joinColumns = @JoinColumn(name = "Unit_id"),
            inverseJoinColumns = @JoinColumn(name = "Employee_id"))*/
    private Unit unit;
    private String position;







    public Employee() {
    }

    public Employee(long id, int recordNumber, String lastName, String firstName, String homeAddress, String telephoneNo, @NotNull LocalDate hireDate, LocalDate leaveDate, Status status, ContractType contractType, Unit unit, String position) {
        this.id = id;
        this.recordNumber = recordNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.homeAddress = homeAddress;
        this.telephoneNo = telephoneNo;
        this.hireDate = hireDate;
        this.leaveDate = leaveDate;
        this.status = status;
        this.contractType = contractType;
        this.unit = unit;
        this.position = position;
        this.setDepartment();
        this.setBusinessUnit();
        this.setCompany();
    }

    public Employee(int recordNumber, String lastName, String firstName, String homeAddress, String telephoneNo, LocalDate hireDate, LocalDate leaveDate, Status status, ContractType contractType, Unit unit, String position) {
        this.recordNumber=recordNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.homeAddress = homeAddress;
        this.telephoneNo = telephoneNo;
        this.hireDate = hireDate;
        this.leaveDate = leaveDate;
        this.status = status;
        this.contractType = contractType;
        this.unit = unit;
        this.position = position;
        this.setDepartment();
        this.setBusinessUnit();
        this.setCompany();
    }


    public long getId() {
        return id;
    }

    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getTelephoneNo() {

        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {

        this.telephoneNo = telephoneNo;
    }

    public LocalDate getHireDate() {

        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public Company getCompany() {
        return company;
    }

    private void setCompany() {
        if(this.unit.getDepartment()!=null)
        this.company = this.businessUnit.getCompany();
    }

    public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    private void setBusinessUnit() {
        if(this.unit.getDepartment()!=null)
        this.businessUnit = this.department.getBusinessUnit();
    }

    public Department getDepartment() {
        return department;
    }

    private void setDepartment() {
        if(this.unit.getDepartment()!=null)
        this.department = this.unit.getDepartment();
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {

        this.unit = unit;
        this.setDepartment();
        this.setBusinessUnit();
        this.setCompany();
    }



    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompanyName() {
        return this.company.getName();
    }

    public String getBusinessUnitName() {
        return this.businessUnit.getName();
    }

    public String getDepartmentName() {
        return this.department.getName();
    }

    public String getUnitName() {
        return this.unit.getName();
    }


}
