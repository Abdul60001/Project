package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addInfo extends AppCompatActivity {
    // ADD COURSE INFROMATION ( COURSE DAY , COURSE HOURS , COURSE CAPACITY , COURSE DESCRIPTION ).

    Button save, back;
    TextView course;

    EditText courseCode,courseName,courseDay, courseHour,courseCapacity,courseDescription;
    DBHandler dbHandler;

    User currentUser;
    Course currentCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);


        course = findViewById(R.id.textView25);
        courseCode=findViewById(R.id.editTextTextPersonName8);
        courseName=findViewById(R.id.editTextTextPersonName18);
        courseDay = findViewById(R.id.editTextTextPersonName12); // title_input
        courseHour = findViewById(R.id.editTextTextPersonName14); //author_input
        courseCapacity= findViewById(R.id.editTextTextPersonName15); //page_input
        courseDescription=findViewById(R.id.editTextTextPersonName16);//text_input
        back = findViewById(R.id.button41);
        save = findViewById(R.id.button40);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler myDB=new DBHandler(addInfo.this);
                myDB.addCourseINFO(courseDay.getText().toString().trim(),
                        courseHour.getText().toString().trim(),
                        Integer.valueOf(courseCapacity.getText().toString().trim()),
                        courseDescription.getText().toString().trim());
            }

        });
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToInstructorStarter();}
        });
    }

    private void goToViewCoursesInstructor() { // To go to another activity when button is pressed
        Intent intent = new Intent(this, ViewCoursesInstructor.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }
    private void goToInstructorStarter() {
        Intent intent = new Intent(this,InstructorStarter.class);
        startActivity(intent);
    }
}