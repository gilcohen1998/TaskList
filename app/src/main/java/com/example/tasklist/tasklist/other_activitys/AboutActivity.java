package com.example.tasklist.tasklist.other_activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bawp.tasklist.R;

public class AboutActivity extends AppCompatActivity {


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

        setContentView(R.layout.about_activity);

        // Setting up the email,instagram,facebook link

        TextView email_text = findViewById(R.id.email_text);
        TextView instagram = findViewById(R.id.instagram_text);
        TextView facebook = findViewById(R.id.facebook_text);

        email_text.setText(Html.fromHtml("<a href=\"mailto:gilc1998@gmail.com\">gilc1998@gmail.com </a>"));
        email_text.setMovementMethod(LinkMovementMethod.getInstance());

        instagram.setText(Html.fromHtml("<a href=\"https://www.instagram.com/gilcohen55/\">gilcohen55 </a>"));
        instagram.setMovementMethod(LinkMovementMethod.getInstance());

        facebook.setText(Html.fromHtml("<a href=\"https://www.facebook.com/profile.php?id=100001783674694\">גיל כהן </a>"));
        facebook.setMovementMethod(LinkMovementMethod.getInstance());


        // The way to set a text color!

     //  text.setTextColor(getResources().getColor(R.color.teal_700));




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
            Intent intent = new Intent(AboutActivity.this, SettingActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id==R.id.more_apps){
            Intent intent = new Intent(AboutActivity.this, More_apps_Activity.class);
            startActivity(intent);
            return true;
        }
        else if(id==R.id.tasks){
            Intent intent = new Intent(AboutActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}