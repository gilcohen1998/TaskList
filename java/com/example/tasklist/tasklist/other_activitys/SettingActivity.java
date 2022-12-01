package com.example.tasklist.tasklist.other_activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import com.bawp.tasklist.R;
import com.example.tasklist.tasklist.Other.MyBounceInterpolator;

public class SettingActivity extends AppCompatActivity {
    private TextView general;
    private Switch night;
    private TextView soontext;
    private TextView app_info;
    private TextView the_version;
    private Switch green;
    private Switch blue;
    private Switch pink;
    private Switch yellow;
    private Switch red;
    private TextView yellow_icon;
    private TextView green_icon;
    private TextView red_icon;
    private TextView blue_icon;
    private TextView pink_icon;

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

        setContentView(R.layout.settings_activity);

        general= findViewById(R.id.general_text);
        night = findViewById(R.id.night_mode_switch);
        soontext= findViewById(R.id.coming_soon_text);
        app_info = findViewById(R.id.app_info_text);
        the_version= findViewById(R.id.the_version_text);
        green = findViewById(R.id.green_theme_switch);
        blue = findViewById(R.id.blue_theme_switch);
        yellow= findViewById(R.id.yellow_theme_switch);
        red= findViewById(R.id.red_theme_switch);
        pink = findViewById(R.id.pink_theme_switch);
        yellow_icon=findViewById(R.id.yellow_theme_text);
        pink_icon = findViewById(R.id.pink_theme_text);
        blue_icon= findViewById(R.id.blue_theme_text);
        red_icon=findViewById(R.id.red_theme_text);
        green_icon=findViewById(R.id.green_theme_text);


        // coming soon!

        night.setEnabled(false);



        soontext.setTextColor(getColor(android.R.color.darker_gray));
        general.setTextColor(getColor(R.color.blue));
        app_info.setTextColor(getColor(R.color.blue));
        the_version.setTextColor(getColor(android.R.color.darker_gray));

        // The saving theme system

        night.setChecked(sharedPreferences.getBoolean("night",false));
        yellow.setChecked(sharedPreferences.getBoolean("yellow",false));
        green.setChecked(sharedPreferences.getBoolean("green",false));
        red.setChecked(sharedPreferences.getBoolean("red",false));
        pink.setChecked(sharedPreferences.getBoolean("pink",false));
        blue.setChecked(sharedPreferences.getBoolean("blue",false));

        if(sharedPreferences.getBoolean("blue",true)){
            yellow.setChecked(false);
            green.setChecked(false);
            red.setChecked(false);
            pink.setChecked(false);
        }

        else if(sharedPreferences.getBoolean("green",true)){
            yellow.setChecked(false);
            blue.setChecked(false);
            red.setChecked(false);
            pink.setChecked(false);
        }

        else if(sharedPreferences.getBoolean("yellow",true)){
            blue.setChecked(false);
            green.setChecked(false);
            red.setChecked(false);
            pink.setChecked(false);
        }

       else if(sharedPreferences.getBoolean("pink",true)){
            yellow.setChecked(false);
            green.setChecked(false);
            red.setChecked(false);
            blue.setChecked(false);
        }

        else if(sharedPreferences.getBoolean("red",true)){
            yellow.setChecked(false);
            green.setChecked(false);
            blue.setChecked(false);
            pink.setChecked(false);
        }
        else{
            yellow.setChecked(false);
            green.setChecked(false);
            blue.setChecked(false);
            pink.setChecked(false);
            blue.setChecked(false);
        }

        green.setOnCheckedChangeListener((compoundButton, ischecked) -> {
            SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
            if (ischecked) {
                editor.putBoolean("green",true);
                editor.apply();
                yellow.setChecked(false);
                blue.setChecked(false);
                red.setChecked(false);
                pink.setChecked(false);
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                finish();

            } else {
                editor.putBoolean("green",false);
                editor.apply();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                finish();


            }
        });

        blue.setOnCheckedChangeListener((compoundButton, ischecked) -> {
            SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
            if (ischecked) {
                editor.putBoolean("blue",true);
                editor.apply();
                green.setChecked(false);
                yellow.setChecked(false);
                red.setChecked(false);
                pink.setChecked(false);
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                finish();

            } else {
                editor.putBoolean("blue",false);
                editor.apply();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                finish();

            }
        });

        red.setOnCheckedChangeListener((compoundButton, ischecked) -> {
            SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
            if (ischecked) {
                editor.putBoolean("red",true);
                editor.apply();
                green.setChecked(false);
                blue.setChecked(false);
                yellow.setChecked(false);
                pink.setChecked(false);
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                finish();

            } else {
                editor.putBoolean("red",false);
                editor.apply();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                finish();

            }
        });

        pink.setOnCheckedChangeListener((compoundButton, ischecked) -> {
            SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
            if (ischecked) {
                editor.putBoolean("pink",true);
                editor.apply();
                green.setChecked(false);
                blue.setChecked(false);
                red.setChecked(false);
                yellow.setChecked(false);
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                finish();


            } else {
                editor.putBoolean("pink",false);
                editor.apply();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                finish();
            }
        });





        yellow.setOnCheckedChangeListener((compoundButton, ischecked) -> {
            SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
            if (ischecked) {
                editor.putBoolean("yellow",true);
                editor.apply();
                green.setChecked(false);
                blue.setChecked(false);
                red.setChecked(false);
                pink.setChecked(false);
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                finish();

            } else {
                editor.putBoolean("yellow",false);
                editor.apply();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                finish();

            }
        });






            // Still not ready!

        night.setOnCheckedChangeListener((compoundButton, ischecked) -> {
            SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
            if (ischecked) {
                editor.putBoolean("night",true);

                // AppCompatDelegate  .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
               // setTheme(R.style.Theme_tasklist_darkmode);
            } else {
                editor.putBoolean("night",false);
                //  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
               // setTheme(R.style.Theme_tasklist);
            }
            editor.apply();
        });
    }



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

        if (id == R.id.tasks) {
            Intent intent = new Intent(SettingActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        else if(id==R.id.about){
            Intent intent = new Intent(SettingActivity.this, AboutActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        else if(id==R.id.more_apps){
            Intent intent = new Intent(SettingActivity.this, More_apps_Activity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}