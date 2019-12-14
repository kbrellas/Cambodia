package com.example.Project1.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    /*@JoinTable( name = "BusinessUnit_Department",
            joinColumns = @JoinColumn(name = "Department_id"),
            inverseJoinColumns = @JoinColumn(name = "BusinessUnit_id"))*/
    private BusinessUnit businessUnit;



    public Department(String name, BusinessUnit businessUnit) {
        this.name = name;
        this.businessUnit = businessUnit;
    }

    public Department(long id, String name, BusinessUnit businessUnit) {
        this.id = id;
        this.name = name;
        this.businessUnit = businessUnit;
    }

    public Department(String name) {
        this.name = name;
    }

    public Department() {
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

    public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }
}
