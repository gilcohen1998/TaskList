package com.example.tasklist.tasklist.other_activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bawp.tasklist.R;
import com.example.tasklist.tasklist.Other.MyBounceInterpolator;

public class More_apps_Activity extends AppCompatActivity {

    private TextView coming_soon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // The choosing theme system

        SharedPreferences sharedPreferences = getSharedPreferences("save",MODE_PRIVATE);

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

        setContentView(R.layout.more_apps_activity);

      coming_soon= findViewById(R.id.coming_soon);

      // Coming soon text animation

      coming_soon.setOnClickListener(view -> {
          final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

          // Use bounce interpolator with amplitude 0.2 and frequency 20
          MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
          myAnim.setInterpolator(interpolator);

          coming_soon.startAnimation(myAnim);


      });

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
            Intent intent = new Intent(More_apps_Activity.this, SettingActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id==R.id.about){
            Intent intent = new Intent(More_apps_Activity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id==R.id.tasks){
            Intent intent = new Intent(More_apps_Activity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}