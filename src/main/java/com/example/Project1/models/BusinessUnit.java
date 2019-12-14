package com.example.Project1.models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "business_unit")
public class BusinessUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    /*@JoinTable( name = "Company_BusinessUnit",
            joinColumns = @JoinColumn(name = "Company_Id"),
            inverseJoinColumns = @JoinColumn(name = "BusinessUnit_id"))*/
    private Company company;



    public BusinessUnit(String name, Company company) {
        this.name = name;
        this.company = company;
    }

    public BusinessUnit(long id, String name, Company company) {
        this.id = id;
        this.name = name;
        this.company = company;
    }

    public BusinessUnit() {
    }

    public BusinessUnit(String name) {
        this.name = name;
    }

    public BusinessUnit(long l, String unisystems) {
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
