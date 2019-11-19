package com.example.Project1.models;

/*
Υλοποιήστε όλα τα CRUD operations για αυτό το Entity που θα επιστρέφουν την πληροφορία(
    id,
    title,
    desc,
    difficulty,
    status). Αν ο μέσος όρος των 3 estimations είναι <2 diffculty = EASY, <=4 MEDIUM, >5 HARD.
 */
public class TaskResponse {
    private long id;
    private String title;
    private String desc;
    private Difficulty difficulty;
    private TaskStatus taskStatus;

    public TaskResponse(long id, String title, String desc, Difficulty difficulty, TaskStatus taskStatus) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.difficulty = difficulty;
        this.taskStatus = taskStatus;
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
}
