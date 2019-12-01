package com.example.Project1.models;

/*
{Δημιουργήστε endpoint όπου θα δέχεται taskId και θα επιστρέφει
 “όλη” την πληροφόρία του Task(
        id,
        title,
        desc,
        difficulty,
        status,
        assignedEmployees,
        updates).}
 */

import java.util.List;

public class FullTaskInfoResponse {
    private long id;
    private String title;
    private String desc;
    private Difficulty difficulty;
    private TaskStatus taskStatus;
    private List<EmployeeResponse> assignedEmployees;
    private List<String> updates;

    public FullTaskInfoResponse(long id, String title, String desc, Difficulty difficulty, TaskStatus taskStatus, List<EmployeeResponse> assignedEmployees, List<String> updates) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.difficulty = difficulty;
        this.taskStatus = taskStatus;
        this.assignedEmployees = assignedEmployees;
        this.updates = updates;
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

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public List<EmployeeResponse> getAssignedEmployees() {
        return assignedEmployees;
    }

    public void setAssignedEmployees(List<EmployeeResponse> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }

    public List<String> getUpdates() {
        return updates;
    }

    public void setUpdates(List<String> updates) {
        this.updates = updates;
    }
}
