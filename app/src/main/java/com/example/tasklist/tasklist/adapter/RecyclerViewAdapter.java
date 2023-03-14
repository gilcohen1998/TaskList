package com.example.tasklist.tasklist.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bawp.tasklist.R;
import com.example.tasklist.tasklist.model.Task;
import com.example.tasklist.tasklist.util.Utils;
import com.google.android.material.chip.Chip;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    // All the tasks

    private final List<Task> taskList;

    private final OnTodoClickListener todoClickListener;

    // Constructor

    public RecyclerViewAdapter(List<Task> taskList, OnTodoClickListener onTodoClickListener) {
        this.taskList = taskList;
        this.todoClickListener= onTodoClickListener;
    }

    // Over ride methods

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_row, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Task task= taskList.get(position);
        holder.task.setText(task.getTask());

        // Change of the colors

        ColorStateList colorStateList = new ColorStateList(new int[][]{
                new int[] {-android.R.attr.state_enabled},
                    new int[] {android.R.attr.state_enabled}
        },
                new int[]{
                        // Color when disabled
                        Color.LTGRAY,

                        // Color when enabled
                        Utils.priorityColor(task)
                });

        String formatted= Utils.formatDate(task.getDueDate());
        holder.todayChip.setText(formatted);

        // Setting up the objects that i want to change
        // their color as a result of the priority choose

        holder.todayChip.setTextColor(Utils.priorityColor(task));
        holder.todayChip.setChipIconTint(colorStateList);
        holder.radioButton.setButtonTintList(colorStateList);



    }

    // How many tasks we have

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    // The widgets in todo_row layout

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        public AppCompatRadioButton radioButton;
        public AppCompatTextView task;
        public Chip todayChip;

        OnTodoClickListener onTodoClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton=itemView.findViewById(R.id.todo_radio_button);
            task=itemView.findViewById(R.id.todo_row_todo);
            todayChip=itemView.findViewById(R.id.todo_row_chip);
            this.onTodoClickListener= todoClickListener;

            itemView.setOnClickListener(this);
            radioButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

           Task currTask= taskList.get(getAdapterPosition());
            int id=view.getId();
            if(id== R.id.todo_row_layout){
                onTodoClickListener.onTodoClick(currTask);
            }
            else if(id==R.id.todo_radio_button){
            onTodoClickListener.onTodoRadioButtonClick(currTask);
            }

        }
    }
}