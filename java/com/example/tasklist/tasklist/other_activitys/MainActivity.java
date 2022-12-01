package com.example.tasklist.tasklist.other_activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawp.tasklist.R;
import com.example.tasklist.tasklist.Other.MyBounceInterpolator;
import com.example.tasklist.tasklist.adapter.OnTodoClickListener;
import com.example.tasklist.tasklist.adapter.RecyclerViewAdapter;
import com.example.tasklist.tasklist.model.SharedViewModel;
import com.example.tasklist.tasklist.model.Task;
import com.example.tasklist.tasklist.model.TaskViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity implements OnTodoClickListener {

    private TaskViewModel taskViewModel;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private SharedViewModel sharedViewModel;
    private SharedPreferences sharedPreferences;
    private FloatingActionButton fab;
    BottomSheetFragment bottomSheetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        sharedPreferences = getSharedPreferences("save",MODE_PRIVATE);


        if(sharedPreferences.getBoolean("yellow",true)){
            setTheme(R.style.Theme_tasklist_yellowtheme);
        }
        else if(sharedPreferences.getBoolean("green",true)){
            setTheme(R.style.Theme_tasklist_greentheme);
        }
        else if(sharedPreferences.getBoolean("blue",true)){
            setTheme(R.style.Theme_tasklist_bluetheme);
        }
        else if(sharedPreferences.getBoolean("pink",true)){
            setTheme(R.style.Theme_tasklist_pinktheme);
        }
        else if(sharedPreferences.getBoolean("red",true)){
            setTheme(R.style.Theme_tasklist_redtheme);
        }
        else {
            setTheme(R.style.Theme_tasklist);
        }

        setContentView(R.layout.activity_main);



        fab = findViewById(R.id.fab);
        if(sharedPreferences.getBoolean("yellow",true)){
            fab.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.yellow_theme)));
        }
        else if(sharedPreferences.getBoolean("green",true)){
            fab.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.green_theme)));
        }
        else if(sharedPreferences.getBoolean("blue",true)){
            fab.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.blue_theme)));
        }
        else if(sharedPreferences.getBoolean("pink",true)){
            fab.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.pink_theme)));
        }
        else if(sharedPreferences.getBoolean("red",true)){
            fab.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.red_theme)));
        }
        else {
            fab.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.default_theme)));
        }



              bottomSheetFragment = new BottomSheetFragment();

     ConstraintLayout constraintLayout = findViewById(R.id.bottomSheet);

      //  BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior = BottomSheetBehavior.from(constraintLayout);
       // bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.STATE_HIDDEN);

        recyclerView= findViewById((R.id.recycler_view));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        taskViewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication())
                .create(TaskViewModel.class);

        sharedViewModel = new ViewModelProvider(this)
                .get(SharedViewModel.class);


        taskViewModel.getAllTasks().observe(this, tasks -> {
            recyclerViewAdapter= new RecyclerViewAdapter(tasks,this);
            recyclerView.setAdapter(recyclerViewAdapter);

        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {

            // Fab animation system

            final Animation myAnim= AnimationUtils.loadAnimation(this,R.anim.bounce);
            fab.startAnimation(myAnim);

            MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
            myAnim.setInterpolator(interpolator);

            fab.startAnimation(myAnim);

          // Add a task

          //  Task task = new Task("Todo", Priority.HIGH, Calendar.getInstance().getTime(),
          //  Calendar.getInstance().getTime(), false);
          //  TaskViewModel.insert(task);

            // Calling the Bottom sheet fragment to show

              showBottonSheetDialog();

        });
    }

    private void showBottonSheetDialog() {
        bottomSheetFragment.show(getSupportFragmentManager(),bottomSheetFragment.getTag());
    }




    // The buttons toolbar menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // The tool bar options

        if (id == R.id.settings) {
            startActivity(getIntent());
            finish();
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id==R.id.about){
            startActivity(getIntent());
            finish();
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id==R.id.more_apps){
            startActivity(getIntent());
            finish();
            Intent intent = new Intent(MainActivity.this, More_apps_Activity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }





    // The happening when u click on the a to do row

    @Override
    public void onTodoClick(Task task) {
        sharedViewModel.selectItem(task);
        sharedViewModel.setIsEdit(true);
        showBottonSheetDialog();
    }


    // The happening when u click the delete button

    @Override
    public void onTodoRadioButtonClick(Task task) {


        AlertDialog.Builder builder;
        builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Notice!")
                .setMessage("Are u sure you want to delete that task?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TaskViewModel.delete(task);
                        recyclerViewAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                        finish();
                        dialog.cancel();
                    }
                })
                .show();

    }



}
