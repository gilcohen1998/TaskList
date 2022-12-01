package com.example.tasklist.tasklist.data;

// help class

import android.app.Application;

import androidx.lifecycle.LiveData;
import com.example.tasklist.tasklist.data.TaskDao;

import com.example.tasklist.tasklist.model.Task;
import com.example.tasklist.tasklist.util.TaskRoomDatabase;

import java.util.List;

public class TasklistRepository {

    private final TaskDao taskDao;

    private final LiveData<List<Task>> allTasks;

    // The repository constructor

    public TasklistRepository(Application application) {
        TaskRoomDatabase database= TaskRoomDatabase.getDatabase(application);
        this.taskDao = database.taskDao();
        this.allTasks = taskDao.getTasks();
    }

    // Get all the existing tasks in the task class

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }


    public void insert(Task task){
        // back infrastructure
        TaskRoomDatabase.databaseWriterExecutor.execute(()-> taskDao.insertTask(task));
    }

    // Get the task that matches the id that we pass

    public LiveData<Task> get(long id){
        return taskDao.get(id);
    }

    // Update the task that we pass

    public void update (Task task){
        // back infrastructure
        TaskRoomDatabase.databaseWriterExecutor.execute(()-> taskDao.update(task));
    }

    // Delete the task that we pass

    public void delete (Task task){
        // back infrastructure
        TaskRoomDatabase.databaseWriterExecutor.execute(()-> taskDao.delete(task));
    }
}
