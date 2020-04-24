package com.example.deathgrips;

import android.media.Image;

import java.time.LocalDate;
import java.util.Date;

public class Task {

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public TaskType getTypeOfTask() {
        return typeOfTask;
    }

    protected String title;
    protected String description;
    protected Date dueDate;
    protected boolean isDone;
    protected Image iconForTask;
    protected TaskType typeOfTask;

    //Constructor for a test task, where we can choose the status
    public Task(String title, String description, Date dueDate, boolean isDone, TaskType typeOfTask){
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isDone = isDone;
        this.typeOfTask = typeOfTask;
    }

    //Constructor for a user-entered task, where the status is always the same (not completed)
    public Task(String title, String description, Date dueDate, TaskType typeOfTask){
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isDone = isDone;
        this.typeOfTask = typeOfTask;
    }

}
