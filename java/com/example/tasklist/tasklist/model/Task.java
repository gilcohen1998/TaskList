package com.example.tasklist.tasklist.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

// task is an entity with the name task_table

@Entity(tableName = "task_table")
public class Task {

    @ColumnInfo(name = "task_id")
    // auto set the unique key
    @PrimaryKey(autoGenerate = true)
    public long taskId;

    public String task;

    public Priority priority;

    @ColumnInfo(name = "due_date")
    public Date dueDate;

    @ColumnInfo(name = "created_at")
    public Date dataCreated;

    @ColumnInfo(name = "is_done")
    public boolean isDone;

    // constructor

    public Task( String task, Priority priority, Date dueDate, Date dataCreated, boolean isDone) {
        this.task = task;
        this.priority = priority;
        this.dueDate = dueDate;
        this.dataCreated = dataCreated;
        this.isDone = isDone;
    }

    // Getters and setters

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDataCreated() {
        return dataCreated;
    }

    public void setDataCreated(Date dataCreated) {
        this.dataCreated = dataCreated;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }


    // The string name of the the variables

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", task='" + task + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", dataCreated=" + dataCreated +
                ", isDone=" + isDone +
                '}';
    }
}
