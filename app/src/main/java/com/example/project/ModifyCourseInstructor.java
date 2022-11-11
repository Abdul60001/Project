package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ModifyCourseInstructor extends AppCompatActivity {
    // INSTRUCTOR CAN ADD OR EDIT COURSE INFORMATION (COURSES: DAY, HOUR , CAPACITY , DESCRIPTION ).
    Button addDetails, editDetails, back;
    TextView addInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_course_instructor);

        addDetails = findViewById(R.id.button30);
        editDetails = findViewById(R.id.button31);
        back= findViewById(R.id.button39);

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToInstructorStarter();}
        });
        addDetails.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToaddinfo();}
        });
        editDetails.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToeditCourseInfo();}
        });
    }

    private void goToInstructorStarter() {
        Intent intent = new Intent(this,InstructorStarter.class);
        startActivity(intent);
    }
    private void goToaddinfo() {
        Intent intent = new Intent(this,addInfo.class);
        startActivity(intent);
    }
    private void goToeditCourseInfo() {
        Intent intent = new Intent(this,editCourseInfo.class);
        startActivity(intent);
    }


}