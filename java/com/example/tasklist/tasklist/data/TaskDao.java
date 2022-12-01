package com.example.tasklist.tasklist.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tasklist.tasklist.model.Task;

import java.util.List;

    // CRUD

    @Dao
    public interface TaskDao {

    @Insert
    void insertTask(Task task);

    // Delete all the data in task table

    @Query("DELETE FROM task_table")
    void deleteAll();

    // Get all the data exists in task table

    @Query(("SELECT * FROM task_table"))
    LiveData<List<Task>> getTasks();


    // Get the task that matches the id that we pass

    @Query(("SELECT * FROM task_table WHERE task_table.task_id == :id "))
    LiveData<Task> get(long id);

    // update the task that we pass

    @Update
    void update(Task task);

    // delete the task that we pass

    @Delete
    void delete(Task task);
}
