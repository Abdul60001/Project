package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

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


        dbHandler = new DBHandler(this);

        currentUser = (User) getIntent().getSerializableExtra("current_user");

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {goTostudent_starter();}
        });
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String code = courseCode.getText().toString();
                String name = courseName.getText().toString();
                String day = courseDay.getText().toString();
                ArrayList<Course> courseSearch = getCoursesByCodeAndNameAndDay(code, name, day);
                goTocourse(courseSearch);
            }
        });
    }

    private void goTostudent_starter() {
        Intent intent = new Intent(this, student_starter.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }
    private void goTocourse(ArrayList<Course> courseSearch) {
        Intent intent = new Intent(this, enrollCourseStudent.class);
        intent.putExtra("current_user", currentUser);
        intent.putExtra("course_search", courseSearch);
        startActivity(intent);
    }

    private ArrayList<Course> getCoursesByCodeAndNameAndDay(String code, String name, String day) {
        Cursor cursor = dbHandler.getCoursesByCodeAndNameAndDay(code, name, day);
        Course result = null;
        ArrayList<Course> courseList = new ArrayList<Course>();
        while (cursor.moveToNext()) {
            result = new Course(Integer.valueOf(cursor.getInt(0)), cursor.getString(1), cursor.getString(2), Integer.valueOf(cursor.getString(3)), cursor.getString(4), cursor.getString(5), cursor.getString(6), Integer.valueOf(cursor.getString(7)));
            courseList.add(result);
        }
        cursor.close();
        return courseList;
    }
}