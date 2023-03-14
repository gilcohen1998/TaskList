package com.example.tasklist.tasklist.other_activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bawp.tasklist.R;


public class StartActivity extends AppCompatActivity {
    private TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);


        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        welcome = findViewById(R.id.welcome_name);
        welcome.setVisibility(View.GONE);

        SharedPreferences sharedPreferences = getSharedPreferences("start",MODE_PRIVATE);
        String FirstTime = sharedPreferences.getString("FirstTimeInstall","");
        SharedPreferences newpref =getSharedPreferences("new",MODE_PRIVATE);

        String name = newpref.getString("name","");

        // Another check (fix for the user get out from the first activity bug)
        // Checking if the user registered his name

        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(name.equals("")){
            editor.putString("FirstTimeInstall", "");
            editor.apply();
            FirstTime="";
        }
        else{
            editor.putString("FirstTimeInstall", "Yes");
            editor.apply();
            FirstTime="Yes";
        }

        // When the app not running firstly

        if(FirstTime.equals("Yes")){


            welcome.setText("Welcome " +newpref.getString("name",""));
            welcome.setVisibility(View.VISIBLE);}




        // The starting animation system

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String finalFirstTime = FirstTime;
        Thread td= new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {

                    // Check if the app running first time or not

                    if(finalFirstTime.equals("Yes")){

                        Intent intent = new Intent(StartActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("FirstTimeInstall", "Yes");

                        editor.apply();
                        SharedPreferences.Editor edit = getSharedPreferences("save",MODE_PRIVATE).edit();
                        edit.putBoolean("green",false);
                        edit.putBoolean("blue",false);
                        edit.putBoolean("yellow",false);
                        edit.putBoolean("red",false);
                        edit.putBoolean("pink",false);

                        edit.apply();

                        Intent intent = new Intent(StartActivity.this, FirstTimeActivity.class);
                        startActivity(intent);
                    }
                    finish();
                }
            }
        }; td.start();
    }
}