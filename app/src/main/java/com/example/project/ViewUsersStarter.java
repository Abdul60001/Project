package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewUsersStarter extends AppCompatActivity {

    Button viewStudents, viewInstructors, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users_starter);

        viewStudents = findViewById(R.id.button7);
        viewInstructors = findViewById(R.id.button8);
        back = findViewById(R.id.button9);

        viewStudents.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {goToViewStudents(); }
        });

        viewInstructors.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {goToViewInstructors(); }
        });

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {goToAdminStarter(); }
        });



        }

    public void goToViewStudents() {
        Intent intent = new Intent(this, ManageUsers.class);
        startActivity(intent);
    }

    public void goToViewInstructors() {
        Intent intent = new Intent(this,ViewInstructors.class);
        startActivity(intent);
    }

    public void goToAdminStarter() {
        Intent intent = new Intent(this,AdminStarter.class);
        startActivity(intent);
    }
}