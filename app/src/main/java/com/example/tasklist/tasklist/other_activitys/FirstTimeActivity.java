package com.example.tasklist.tasklist.other_activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawp.tasklist.R;

public class FirstTimeActivity extends AppCompatActivity {
    private EditText name;
    private Button start_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.first_time_activity);

        // This activity run one time (when the user install the app)

        SharedPreferences sharedPreferences = getSharedPreferences("new",MODE_PRIVATE);
        sharedPreferences.getString("name","");

        name = findViewById(R.id.choose_name);
        start_button =findViewById(R.id.start_button);

        start_button.setOnClickListener(view -> {

           String Name= name.getText().toString().trim();
           if(Name.isEmpty()){
               Toast.makeText(this,"Empty name",Toast.LENGTH_SHORT).show();
           }
           else{
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name",Name);
            editor.apply();

               Intent intent = new Intent(FirstTimeActivity.this, MainActivity.class);
               startActivity(intent);
           }

        });


    }
}