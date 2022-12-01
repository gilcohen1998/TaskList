package com.example.tasklist.tasklist.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tasklist.tasklist.data.TasklistRepository;

import java.util.List;

    // the connector between UI to all the data

public class TaskViewModel extends AndroidViewModel {

    // Repository

    public static TasklistRepository repository;

    // Every task in Task class

    public final LiveData<List<Task>> allTasks;


    // Constructor

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository= new TasklistRepository(application);
        allTasks=repository.getAllTasks();
    }

    // Getter for alltasks

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }

    // Insert the task that u pass

    public static void insert(Task task){
        repository.insert(task);
    }

    // Get the task with the id that u pass

    public LiveData<Task> get(long id){
        return  repository.get(id);
    }

    // Update the task that u pass

    public static void update(Task task){
        repository.update(task);
    }

    // Delete the task that u pass

    public static void delete(Task task){
        repository.delete(task);
    }
}
