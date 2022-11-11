package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import java.util.ResourceBundle;

public class AssignCourse extends AppCompatActivity {

    // Instructor assign course to him self, after clicking the assign button , the assigned courses should show in "VIEW ASSGINED COURSES WINDOW"


    Button back, assign;
    TextView assignCourse;
    EditText courseCode, courseName;
    DBHandler dbHandler;

    User currentUser;
    Course currentCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_course);

        assignCourse = findViewById(R.id.textView23);
        courseCode = findViewById(R.id.editTextTextPersonName11);
        courseName = findViewById(R.id.editTextTextPersonName13);
        assign = findViewById(R.id.button37);
        back = findViewById(R.id.button38);

        dbHandler = new DBHandler(this);

        currentCourse = (Course) getIntent().getSerializableExtra("selected_course");
        currentUser = (User) getIntent().getSerializableExtra("current_user");

        if (currentCourse != null) {
            assignCourse.setText(currentCourse.getCode());
            courseCode.setText(currentCourse.getCode());
            courseName.setText(currentCourse.getName());
        }
        assign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String courseCodeInput = courseCode.getText().toString();
                String courseNameInput = courseName.getText().toString();

                if(currentCourse!=null) {
                    currentCourse.setCode(courseCodeInput);
                    currentCourse.setName(courseNameInput);

                    dbHandler.updateCourseById(currentCourse.getId(), currentCourse);
                }
                else {
                    dbHandler.assignCourse(new Course(courseCodeInput, courseNameInput));
                }

                goToviewAssignedCoursesInstructor();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToInstructorStarter();
            }
        });

    }

    private void goToviewAssignedCoursesInstructor() {
        Intent intent = new Intent(this, viewAssignedCoursesInsutructor.class);
        startActivity(intent);
    }
    private void goToInstructorStarter() {
        Intent intent = new Intent(this, InstructorStarter.class);
        startActivity(intent);
    }
}

