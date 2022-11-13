package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class EditCourseInfo extends AppCompatActivity {

    Button update, back;
    TextView course;

    EditText courseDay, courseHours,courseCapacity,courseDescription;
    DBHandler dbHandler;

    User currentUser;
    Course currentCourse;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course_info);

        builder = new AlertDialog.Builder(this);

        currentUser = (User) getIntent().getSerializableExtra("current_user");
        currentCourse = (Course) getIntent().getSerializableExtra("selected_course");

        dbHandler = new DBHandler(this);

        course = findViewById(R.id.textView24);
        courseDay = findViewById(R.id.editTextTextPersonName7); // title_input
        courseHours = findViewById(R.id.editTextTextPersonName9); //author_input
        courseCapacity= findViewById(R.id.editTextTextPersonName10); //page_input
        courseDescription=findViewById(R.id.editTextTextPersonName17);//text_input
        back = findViewById(R.id.button43);
        update = findViewById(R.id.button42);

        course.setText("Edit " + currentCourse.getCode() + " Course Information");
        courseDay.setText(currentCourse.getCourseDay());
        courseHours.setText(currentCourse.getCourseHours());
        courseDescription.setText(currentCourse.getDescription());
        courseCapacity.setText(String.valueOf(currentCourse.getCapacity()));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseDayInput = courseDay.getText().toString().trim();
                String courseHoursInput = courseHours.getText().toString().trim();
                String courseDescriptionInput = courseDescription.getText().toString().trim();
                String courseCapacityInput = courseCapacity.getText().toString().trim();

                if(!validDay(courseDayInput)) {
                    displayDialogWithMessage("Invalid course day input.");
                }
                else if(!validHours(courseHoursInput)) {
                    displayDialogWithMessage("Invalid course hours input.");
                }
                else if(!validCapacity(courseCapacityInput)) {
                    displayDialogWithMessage("Invalid course capacity input.");
                }
                else {
                    currentCourse.setCourseDay(courseDayInput);
                    currentCourse.setCourseHours(courseHoursInput);
                    currentCourse.setDescription(courseDescriptionInput);
                    currentCourse.setCapacity(Integer.parseInt(courseCapacityInput));

                    dbHandler.updateCourseById(currentCourse.getId(), currentCourse);
                    goToInstructorStarter();
                }
            }

        });
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                goToModifyCoursesInstructor();}
        });
    }

    private void goToInstructorStarter() {
        Intent intent = new Intent(this, InstructorStarter.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }
    private void goToModifyCoursesInstructor() {
        Intent intent = new Intent(this, ModifyCoursesInstructor.class);
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

    private boolean validDay(String inputDay) {
        String[] validDays = new String[]{"sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
        for(int i = 0; i < validDays.length; i++) {
            if(inputDay.toLowerCase().equals(validDays[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean validHours(String inputHours) {
        String timePattern = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        return Pattern.compile(timePattern).matcher(inputHours).matches();
    }

    private boolean validCapacity(String inputCapacity) {
        int inputCapacityIntegerValue;

        try {
            inputCapacityIntegerValue = Integer.parseInt(inputCapacity);
        } catch (NumberFormatException e) {
            return false;
        }

        return inputCapacityIntegerValue >= 0;
    }
}