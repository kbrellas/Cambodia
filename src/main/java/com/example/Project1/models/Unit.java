package com.example.Project1.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "unit")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
   /* @JoinTable( name = "Department_Unit",
            joinColumns = @JoinColumn(name = "Unit_id"),
            inverseJoinColumns = @JoinColumn(name = "Department_id"))*/
    private Department department;



    public Unit(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public Unit(long id) {
        this.id = id;
    }

    public Unit(long id, String name, Department department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public Unit(String name) {
        this.name = name;
    }

    public Unit() {
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
