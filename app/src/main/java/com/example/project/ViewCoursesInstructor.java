package com.example.project;

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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewCoursesInstructor extends AppCompatActivity {
    //View all available courses to instructor

    Button back;
    ListView coursesListView;

    ArrayList<Course> coursesList;
    DBHandler dbHandler;
    String[] courseCodeStringList;

    User currentUser;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course_insturctor);

        builder = new AlertDialog.Builder(this);

        back = findViewById(R.id.button32);
        coursesListView = findViewById(R.id.ListView3);

        currentUser = (User) getIntent().getSerializableExtra("current_user");

        dbHandler = new DBHandler(this);
        coursesList = new ArrayList<>();
        syncCourseList();
        syncCoursesListView();

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToInstructorStarter();}
        });

        coursesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course selectedCourse = coursesList.get(position);
                User selectedCourseInstructor = getInstructorById(selectedCourse.getInstructorId());
                String display = "";

                if(selectedCourseInstructor!=null) {
                    display = "Course Code: "+selectedCourse.getCode()+"\n"+
                            "Course Name: "+selectedCourse.getName()+"\n"+
                            "Instructor: "+selectedCourseInstructor.getUsername()+"\n"+
                            "Day: "+selectedCourse.getCourseDay()+"\n"+
                            "Hours: "+selectedCourse.getCourseHours()+"\n"+
                            "Description: "+selectedCourse.getDescription()+"\n"+
                            "Capacity: "+selectedCourse.getCapacity();
                }
                else {
                    display = "Course Code: "+selectedCourse.getCode()+"\n"+
                            "Course Name: "+selectedCourse.getName()+"\n"+
                            "Instructor: No Instructor Found\n"+
                            "Day: "+selectedCourse.getCourseDay()+"\n"+
                            "Hours: "+selectedCourse.getCourseHours()+"\n"+
                            "Description: "+selectedCourse.getDescription()+"\n"+
                            "Capacity: "+selectedCourse.getCapacity();
                }

                displayDialogWithMessage(display, selectedCourse);
            }
        });

    }

    private void displayDialogWithMessage(String message, Course course) {
        builder.setMessage(message)
                .setCancelable(false)
                .setNeutralButton("View Enrolled Students", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        goToViewEnrolledStudents(course);
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void goToInstructorStarter() {
        Intent intent = new Intent(this,InstructorStarter.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }

    private void goToViewEnrolledStudents(Course course) {
        Intent intent = new Intent(this,ViewEnrolledStudents.class);
        intent.putExtra("current_user", currentUser);
        intent.putExtra("selected_course", course);
        startActivity(intent);
    }

    private void syncCourseList() {
        coursesList.clear();
        Cursor cursor = dbHandler.getCourses();
        while (cursor.moveToNext()) {
            coursesList.add(new Course(Integer.valueOf(cursor.getInt(0)), cursor.getString(1), cursor.getString(2), Integer.valueOf(cursor.getString(3)), cursor.getString(4), cursor.getString(5), cursor.getString(6), Integer.valueOf(cursor.getString(7))));
        }
        cursor.close();

        getCourseCodesFromCourseList();
    }

    private void getCourseCodesFromCourseList() {
        String[] result = new String[coursesList.size()];
        int counter=0;
        for(Course c: coursesList) {
            result[counter] = c.getCode();
            counter++;
        }
        courseCodeStringList = result;
    }

    private void syncCoursesListView() {
        ArrayAdapter<String> usernameListAdapter = new ArrayAdapter<String>(this, R.layout.simple_list_item, R.id.textView10, courseCodeStringList);
        coursesListView.setAdapter(usernameListAdapter);
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
