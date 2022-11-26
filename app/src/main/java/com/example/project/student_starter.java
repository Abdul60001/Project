package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class student_starter extends AppCompatActivity {
    /** Student Starter class */

    Button viewCoursesStudent, searchEnrolleCourseStudent,viewEnrolledCourse, back;
    User currentUser;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_starter);

        viewCoursesStudent = findViewById(R.id.button31);
        searchEnrolleCourseStudent = findViewById(R.id.button39);
        viewEnrolledCourse=findViewById(R.id.button46);
        back = findViewById(R.id.button47);

        dbHandler = new DBHandler(this);

        currentUser = (User) getIntent().getSerializableExtra("current_user");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWelcomePage();
            }
        });

        viewCoursesStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {goToviewCoursesStudent();}
        });

        searchEnrolleCourseStudent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToenrollCourseStudent();}
        });

        viewEnrolledCourse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToviewEnrolledCourse();}
        });

    }

    private void goToWelcomePage() {
        Intent intent = new Intent(this, WelcomePage.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }
    private void goToviewCoursesStudent() {
        Intent intent = new Intent(this, Course.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }


    private void goToenrollCourseStudent() {
        Intent intent = new Intent(this, studentSearchCourse.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }

    private void goToviewEnrolledCourse() {
        Intent intent = new Intent(this, viewenrolledCoursesStudent.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }


}