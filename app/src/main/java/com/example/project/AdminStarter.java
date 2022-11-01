package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminStarter extends AppCompatActivity {

    Button manageUsers, viewCourses, back;

    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_starter);

        manageUsers = findViewById(R.id.button4);
        viewCourses = findViewById(R.id.button5);
        back = findViewById(R.id.button6);

        currentUser = (User) getIntent().getSerializableExtra("current_user");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWelcomePage();
            }
        });

        manageUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {goToManageUsers();}
        });

        viewCourses.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToManageCourses();}
        });
    }

    private void goToWelcomePage() {
        Intent intent = new Intent(this, WelcomePage.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }

    private void goToManageUsers() {
        Intent intent = new Intent(this,ManageUsers.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }

    private void goToManageCourses() {
        Intent intent = new Intent(this, ManageCourses.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }
}