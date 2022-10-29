package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminStarter extends AppCompatActivity {

    Button viewUsers, viewCourses, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_starter);

        viewUsers = findViewById(R.id.button4);
        viewCourses = findViewById(R.id.button5);
        back = findViewById(R.id.button6);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWelcomePage();
            }
        });
    }

    private void goToWelcomePage() {
        Intent intent = new Intent(this, WelcomePage.class);
        startActivity(intent);
    }
}