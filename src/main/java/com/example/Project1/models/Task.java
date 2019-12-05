package com.example.Project1.models;

import javax.persistence.*;
import java.util.List;



@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String desc;
    private int estimationA;
    private int estimationB;
    private int estimationC;
    private TaskStatus taskStatus;
    @ElementCollection
    private List<String> updates;
    @ManyToMany
    @JoinTable( name = "Employee_Task",
                joinColumns = @JoinColumn(name = "Task_id"),
                inverseJoinColumns = @JoinColumn(name = "Employee_id"))
    private List<Employee> employees;


    public Task(String title, String desc, int estimationA, int estimationB, int estimationC, TaskStatus taskStatus, List<String> updates, List<Employee> employees) {
        this.title = title;
        this.desc = desc;
        this.estimationA = estimationA;
        this.estimationB = estimationB;
        this.estimationC = estimationC;
        this.taskStatus = taskStatus;
        this.updates = updates;
        this.employees = employees;
    }

    public Task() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getEstimationA() {
        return estimationA;
    }

    public void setEstimationA(int estimationA) {
        this.estimationA = estimationA;
    }

    public int getEstimationB() {
        return estimationB;
    }

    public void setEstimationB(int estimationB) {
        this.estimationB = estimationB;
    }

    public int getEstimationC() {
        return estimationC;
    }

    public void setEstimationC(int estimationC) {
        this.estimationC = estimationC;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public List<String> getUpdates() {
        return updates;
    }

    public void setUpdates(List<String> updates) {
        this.updates = updates;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
