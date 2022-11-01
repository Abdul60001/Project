package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditCourses extends AppCompatActivity {

    Button delete, save;
    TextView course;
    EditText courseCode, courseName;

    DBHandler dbHandler;

    User currentUser;
    Course currentCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_courses);

        course = findViewById(R.id.textView11);
        courseCode = findViewById(R.id.editTextTextPersonName5);
        courseName = findViewById(R.id.editTextTextPersonName6);
        delete = findViewById(R.id.button10);
        save = findViewById(R.id.button21);

        dbHandler = new DBHandler(this);

        currentCourse = (Course) getIntent().getSerializableExtra("selected_course");
        currentUser = (User) getIntent().getSerializableExtra("current_user");

        if(currentCourse!=null) {
            course.setText(currentCourse.getCode());
            courseCode.setText(currentCourse.getCode());
            courseName.setText(currentCourse.getName());
        }
        else {
            course.setText("New Course");
        }

        delete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                if(currentCourse!=null) {
                    dbHandler.deleteCourseById(currentCourse.getId());
                }

                goToManageCourses();
            }
        });

        save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String courseCodeInput = courseCode.getText().toString();
                String courseNameInput = courseName.getText().toString();

                if(currentCourse!=null) {
                    currentCourse.setCode(courseCodeInput);
                    currentCourse.setName(courseNameInput);

                    dbHandler.updateCourseById(currentCourse.getId(), currentCourse);
                }
                else {
                    dbHandler.addCourse(new Course(courseCodeInput, courseNameInput));
                }

                goToManageCourses();
            }
        });
    }

    private void goToManageCourses() {
        Intent intent = new Intent(this, ManageCourses.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }

}