package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class editCourseInfo extends AppCompatActivity {

    Button save, back;
    TextView course;

    EditText courseDay, courseHour,courseCapacity,courseDescription;
    DBHandler dbHandler;

    User currentUser;
    Course currentCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);


        course = findViewById(R.id.textView25);
        courseDay = findViewById(R.id.editTextTextPersonName7); // title_input
        courseHour = findViewById(R.id.editTextTextPersonName9); //author_input
        courseCapacity= findViewById(R.id.editTextTextPersonName10); //page_input
        courseDescription=findViewById(R.id.editTextTextPersonName17);//text_input
        back = findViewById(R.id.button41);
        save = findViewById(R.id.button40);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler myDB=new DBHandler(editCourseInfo.this);
                myDB.editCourseINFO(courseDay.getText().toString().trim(),
                        courseHour.getText().toString().trim(),
                        Integer.valueOf(courseCapacity.getText().toString().trim()),
                        courseDescription.getText().toString().trim());
            }

        });
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToModifyCourseInstructor();}
        });
    }

    private void goToViewCoursesInstructor() { // To go to another activity when button is pressed
        Intent intent = new Intent(this, ViewCoursesInstructor.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }
    private void goToModifyCourseInstructor() {
        Intent intent = new Intent(this,ModifyCourseInstructor.class);
        startActivity(intent);
    }
}