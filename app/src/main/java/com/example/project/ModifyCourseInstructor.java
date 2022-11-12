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
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_course_instructor);

        addDetails = findViewById(R.id.button30);
        editDetails = findViewById(R.id.button31);
        back= findViewById(R.id.button39);

        currentUser = (User) getIntent().getSerializableExtra("current_user");

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToInstructorStarter();}
        });
        addDetails.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                goToAddInfo();}
        });
        editDetails.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                goToEditCourseInfo();}
        });
    }

    private void goToInstructorStarter() {
        Intent intent = new Intent(this,InstructorStarter.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }
    private void goToAddInfo() {
        Intent intent = new Intent(this, AddInfo.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }
    private void goToEditCourseInfo() {
        Intent intent = new Intent(this, EditCourseInfo.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }


}