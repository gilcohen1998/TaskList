package com.example.tasklist.tasklist.adapter;

import com.example.tasklist.tasklist.model.Task;

public interface OnTodoClickListener {
    void onTodoClick(Task task);
    void onTodoRadioButtonClick(Task task);
}
