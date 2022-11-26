package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class viewenrolledCoursesStudent extends AppCompatActivity {
    /**Class to view enrolled Courses by student */

    Button back;
    ListView enrolledCoursesView;
    ArrayList<Course> enrolledCourses;
    DBHandler dbHandler;
    String[] enrolledCourseCodeStringList;

    User currentUser;

    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewenrolled_courses_student);

        builder = new AlertDialog.Builder(this);

        back = findViewById(R.id.button45);
        enrolledCoursesView = findViewById(R.id.listview20);


        currentUser = (User) getIntent().getSerializableExtra("current_user");
        dbHandler = new DBHandler(this);
        enrolledCourses = new ArrayList<>();


        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToStudentStarter();
            }
        });
    }

    private void goToStudentStarter() {
        Intent intent = new Intent(this, student_starter.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }



}