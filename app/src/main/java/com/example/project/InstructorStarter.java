package com.example.project;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;


public class InstructorStarter extends AppCompatActivity {
    Button viewCourse, assignCourse,viewAssignedCourses,addEdit, back;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_starter);

        viewCourse = findViewById(R.id.button33);
        assignCourse = findViewById(R.id.button34);
        viewAssignedCourses=findViewById(R.id.button44);
        addEdit = findViewById(R.id.button36);
        back = findViewById(R.id.button35);

        currentUser = (User) getIntent().getSerializableExtra("current_user");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWelcomePage();
            }
        });

        viewCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {goToViewCoursesInstructor();}
        });

        assignCourse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToAssignCourse();}
        });
        viewAssignedCourses.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToViewAssignedCoursesInstructor();}
        });

        addEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToModifyCourseInstructor();}
        });

    }
    private void goToWelcomePage() {
        Intent intent = new Intent(this, WelcomePage.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }

    private void goToViewCoursesInstructor() {
        Intent intent = new Intent(this, ViewCoursesInstructor.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }
    private void goToViewAssignedCoursesInstructor() {
        Intent intent = new Intent(this, ViewAssignedCoursesInstructor.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }

    private void goToAssignCourse() {
        Intent intent = new Intent(this, AssignCourse.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }

    private void goToModifyCourseInstructor() {
        Intent intent = new Intent(this,ModifyCourseInstructor.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }

}