package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

public class AssignCourse extends AppCompatActivity {

    // Instructor assign course to him self, after clicking the assign button , the assigned courses should show in "VIEW ASSGINED COURSES WINDOW"


    Button back, assign;
    TextView assignCourse;
    EditText courseCode, courseName;
    DBHandler dbHandler;

    User currentUser;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_course);

        builder = new AlertDialog.Builder(this);

        assignCourse = findViewById(R.id.textView23);
        courseCode = findViewById(R.id.editTextTextPersonName11);
        courseName = findViewById(R.id.editTextTextPersonName13);
        assign = findViewById(R.id.button37);
        back = findViewById(R.id.button38);

        dbHandler = new DBHandler(this);

        currentUser = (User) getIntent().getSerializableExtra("current_user");

        assign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String courseCodeInput = courseCode.getText().toString();
                String courseNameInput = courseName.getText().toString();

                Course courseSearch = getCourseByCodeAndName(courseCodeInput, courseNameInput);

                if(courseSearch!=null) {
                    if(courseSearch.getInstructorId()==-1) {
                        courseSearch.setInstructorId(currentUser.getId());
                        dbHandler.updateCourseById(courseSearch.getId(), courseSearch);
                        goToViewAssignedCoursesInstructor();
                    }
                    else {
                        User courseSearchInstructor = getInstructorById(courseSearch.getInstructorId());
                        displayDialogWithMessage("Course already assigned to " + courseSearchInstructor.getUsername());
                    }
                }
                else {
                    displayDialogWithMessage("No course found.");
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToInstructorStarter();
            }
        });

    }

    private void goToViewAssignedCoursesInstructor() {
        Intent intent = new Intent(this, ViewAssignedCoursesInstructor.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }
    private void goToInstructorStarter() {
        Intent intent = new Intent(this, InstructorStarter.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }

    private void displayDialogWithMessage(String message) {
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private Course getCourseByCodeAndName(String code, String name) {
        Cursor cursor = dbHandler.getCoursesByCodeAndName(code, name);
        Course result = null;
        if (cursor.moveToNext()) {
            result = new Course(Integer.valueOf(cursor.getInt(0)), cursor.getString(1), cursor.getString(2), Integer.valueOf(cursor.getString(3)), cursor.getString(4), cursor.getString(5), cursor.getString(6), Integer.valueOf(cursor.getString(7)));
        }
        cursor.close();
        return result;
    }

    private User getInstructorById(int id) {
        Cursor cursor = dbHandler.getUsersById(id);
        User result = null;
        if (cursor.moveToNext()) {
            result = new User(Integer.valueOf(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }
        cursor.close();
        return result;
    }
}

