package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewEnrolledStudents extends AppCompatActivity {

    Button back;
    ListView studentsListView;

    ArrayList<Course> studentsList;
    DBHandler dbHandler;
    String[] studentsStringList;

    User currentUser;
    Course selectedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_enrolled_students);

        back = findViewById(R.id.button52);
        studentsListView = findViewById(R.id.ListView300);

        currentUser = (User) getIntent().getSerializableExtra("current_user");
        selectedCourse = (Course) getIntent().getSerializableExtra("selected_course");

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToViewCoursesInstructor();}
        });
    }

    private void goToViewCoursesInstructor() {
        Intent intent = new Intent(this,ViewCoursesInstructor.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }
}