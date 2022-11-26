package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class studentSearchCourse extends AppCompatActivity {
    /** Search and Enroll Course */ 
    Button back, search;
    EditText courseCode, courseName,courseDay;
    DBHandler dbHandler;


    User currentUser;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search_course);

        courseCode = findViewById(R.id.editTextTextPersonName19);
        courseName = findViewById(R.id.editTextTextPersonName20);
        courseDay = findViewById(R.id.editTextTextPersonName21);
        search = findViewById(R.id.button21);
        back = findViewById(R.id.button48);

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goTostudent_starter();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goTocourse();
            }
        });


    }

    private void goTostudent_starter() {
        Intent intent = new Intent(this, student_starter.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }
    private void goTocourse() {
        Intent intent = new Intent(this, Course.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }




}