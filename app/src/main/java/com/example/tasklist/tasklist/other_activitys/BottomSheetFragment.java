package com.example.tasklist.tasklist.other_activitys;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.lifecycle.ViewModelProvider;

import com.bawp.tasklist.R;
import com.example.tasklist.tasklist.model.Priority;
import com.example.tasklist.tasklist.model.SharedViewModel;
import com.example.tasklist.tasklist.model.Task;
import com.example.tasklist.tasklist.model.TaskViewModel;
import com.example.tasklist.tasklist.util.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;

import java.util.Calendar;
import java.util.Date;

public class BottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    private EditText enterTodo;
    private ImageButton calendarButton;
    private ImageButton priorityButton;
    private RadioGroup priorityRadioGroup;
    private RadioButton selectedRadioButton;
    private ImageButton saveButton;
    private CalendarView calendarView;
    private Group calendarGroup;
    private int selectedButtonId;
    private Date dueDate;
    private SharedViewModel sharedViewModel;
    private boolean isEdit;
    private Priority priority;
    private ImageButton save_icon;
    private ImageButton date;
    private ImageButton priority_icon;
    private Chip today;
    private Chip tomorrow;
    private Chip nextweek;

    Calendar calendar= Calendar.getInstance();

    public BottomSheetFragment(){}

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {


        View view = inflater.inflate(R.layout.bottom_sheet,container,false);


        calendarGroup=view.findViewById(R.id.calendar_group);
        calendarView=view.findViewById(R.id.calendar_view);
        calendarButton=view.findViewById(R.id.today_calendar_button);
        enterTodo= view.findViewById(R.id.enter_todo_et);
        saveButton=view.findViewById(R.id.save_todo_button);
        priorityButton=view.findViewById(R.id.priority_todo_button);
        priorityRadioGroup=view.findViewById(R.id.radioGroup_priority);
        save_icon= (ImageButton)view.findViewById(R.id.save_todo_button);
        date= (ImageButton)view.findViewById(R.id.today_calendar_button);
        priority_icon= (ImageButton)view.findViewById(R.id.priority_todo_button);
        today=(Chip)view.findViewById(R.id.today_chip);
        tomorrow=(Chip)view.findViewById(R.id.tomorrow_chip);
        nextweek=(Chip)view.findViewById(R.id.next_week_chip);


        Chip todayChip= view.findViewById(R.id.today_chip);
        todayChip.setOnClickListener(this);
        Chip tomorrowChip=view.findViewById(R.id.tomorrow_chip);
        tomorrowChip.setOnClickListener(this);
        Chip nextWeekChip = view.findViewById(R.id.next_week_chip);
        nextWeekChip.setOnClickListener(this);

        //The icons change color system

        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("save", Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("blue",true)) {
            save_icon.setImageResource(R.drawable.blue_save_button);
            date.setImageResource(R.drawable.blue_change_date);
            priority_icon.setImageResource(R.drawable.blue_priority_icon);
            today.setChipIconResource(R.drawable.blue_today_icon);
            tomorrow.setChipIconResource(R.drawable.blue_tomorrow_icon);
            nextweek.setChipIconResource(R.drawable.blue_nextweek_icon);
        }

        else if(sharedPreferences.getBoolean("green",true)) {
            save_icon.setImageResource(R.drawable.green_save_button);
            date.setImageResource(R.drawable.green_change_date);
            priority_icon.setImageResource(R.drawable.green_priority_icon);
            today.setChipIconResource(R.drawable.green_today_icon);
            tomorrow.setChipIconResource(R.drawable.green_tomorrow_icon);
            nextweek.setChipIconResource(R.drawable.green_nextweek_icon);
        }

        else if(sharedPreferences.getBoolean("red",true)) {
            save_icon.setImageResource(R.drawable.red_save_button);
            date.setImageResource(R.drawable.red_change_date);
            priority_icon.setImageResource(R.drawable.red_priority_icon);
            today.setChipIconResource(R.drawable.red_today_icon);
            tomorrow.setChipIconResource(R.drawable.red_tomorrow_icon);
            nextweek.setChipIconResource(R.drawable.red_nextweek_icon);
        }

        else if(sharedPreferences.getBoolean("yellow",true)) {
            save_icon.setImageResource(R.drawable.yellow_save_button);
            date.setImageResource(R.drawable.yellow_change_date);
            priority_icon.setImageResource(R.drawable.yellow_priority_icon);
            today.setChipIconResource(R.drawable.yellow_today_icon);
            tomorrow.setChipIconResource(R.drawable.yellow_tomorrow_icon);
            nextweek.setChipIconResource(R.drawable.yellow_nextweek_icon);
        }

        else if(sharedPreferences.getBoolean("pink",true)) {
            save_icon.setImageResource(R.drawable.pink_save_button);
            date.setImageResource(R.drawable.pink_change_date);
            priority_icon.setImageResource(R.drawable.pink_priority_icon);
            today.setChipIconResource(R.drawable.pink_today_icon);
            tomorrow.setChipIconResource(R.drawable.pink_tomorrow_icon);
            nextweek.setChipIconResource(R.drawable.pink_nextweek_icon);
        }

        else{
            save_icon.setImageResource(R.drawable.default_save_button);
            date.setImageResource(R.drawable.default_change_date);
            priority_icon.setImageResource(R.drawable.default_priority_icon);
            today.setChipIconResource(R.drawable.default_today_icon);
            tomorrow.setChipIconResource(R.drawable.default_tomorrow_icon);
            nextweek.setChipIconResource(R.drawable.default_nextweek_icon);
        }


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(sharedViewModel.getSelectedItem().getValue()!=null) {
           isEdit=sharedViewModel.getIsEdit();
            Task task= sharedViewModel.getSelectedItem().getValue();
            enterTodo.setText(task.getTask());
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModel= new ViewModelProvider(requireActivity())
                .get((SharedViewModel.class));

              // The happening when u click the priority button

          priorityButton.setOnClickListener(view13 -> {

              // Hide the keyboard

              Utils.hideSoftKeyboard(view13);

              // Show the priority if the button was clicked and
              // if clicked again the button disappear

              priorityRadioGroup.setVisibility
                      (priorityRadioGroup.getVisibility()== View.GONE ? View.VISIBLE
                              : View.GONE);

              // Check which button was clicked (HIGH,MED,LOW)

              priorityRadioGroup.setOnCheckedChangeListener((radioGroup, checkedId) -> {

                if(priorityRadioGroup.getVisibility()==View.VISIBLE) {
                selectedButtonId=checkedId;
                selectedRadioButton=view.findViewById(selectedButtonId);

                if(selectedRadioButton.getId()==R.id.radioButton_high){
                    priority= Priority.HIGH;
                }
                else if(selectedRadioButton.getId()==R.id.radioButton_med){
                    priority= Priority.MEDIUM;
                }
                else if(selectedRadioButton.getId()==R.id.radioButton_low){
                    priority=Priority.LOW;
                }
                else priority=Priority.LOW;
                }
                else priority=Priority.LOW;

              });
          });

            // The save button files

        // The happening when u click the save button
        saveButton.setOnClickListener(view12 -> {

            // puts the edit text enterTodo into string

            String task = enterTodo.getText().toString().trim();
            // Check if the string task is empty

            if(!TextUtils.isEmpty(task)&& dueDate!=null&& priority!=null){
                Task myTask= new Task(task, priority,
                        dueDate,
                        Calendar.getInstance().getTime(),
                        false);

                if(isEdit){
                    Task updateTask=sharedViewModel.getSelectedItem().getValue();
                    updateTask.setTask(task);
                    updateTask.setDataCreated(Calendar.getInstance().getTime());
                    updateTask.setPriority(priority);
                    updateTask.setDueDate(dueDate);
                    TaskViewModel.update(updateTask);
                    sharedViewModel.setIsEdit(false);
                }else{
                TaskViewModel.insert(myTask);
                }
                dueDate= null;
                enterTodo.setText("");

                // Dismiss the BottomSheet window

                if(this.isVisible()){
                    this.dismiss();
                }

        }
        });

            // The happening when u click on a date in the calender

        calendarView.setOnDateChangeListener((calendarView, year, month, day) -> {
        calendar.clear();
        calendar.set(year,month,day);
        dueDate=calendar.getTime();


           });





                      // The happening when u click the calendar button

                      calendarButton.setOnClickListener(view1 -> {

                          // opening and closing system

                          calendarGroup.setVisibility
                          (calendarGroup.getVisibility()== View.GONE ? View.VISIBLE
                              : View.GONE);
                          Utils.hideSoftKeyboard(view1);
        });

    }

    @Override
    public void onClick(View view) {

        int id= view.getId();

        // Today button

        if(id==R.id.today_chip){
            calendar.add(Calendar.DAY_OF_YEAR,0);
            dueDate=calendar.getTime();
        }

        // Tomorrow button

        else if(id==R.id.tomorrow_chip){
            calendar.add(Calendar.DAY_OF_YEAR,1);
            dueDate=calendar.getTime();
        }

        // Next week button

        else if(id==R.id.next_week_chip){
            calendar.add(Calendar.DAY_OF_YEAR,7);
            dueDate=calendar.getTime();
        }


    }
}