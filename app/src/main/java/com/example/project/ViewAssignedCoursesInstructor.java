package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewAssignedCoursesInstructor extends AppCompatActivity {

    // View assigned courses by instructor

    Button back;
    ListView assignedCoursesView;
    ArrayList<Course> assignedCourses;
    DBHandler dbHandler;
    String[] assignedcourseCodeStringList;


    User currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_assigned_courses_insutructor);
        back = findViewById(R.id.button45);
        assignedCoursesView = findViewById(R.id.listview4);


        currentUser = (User) getIntent().getSerializableExtra("current_user");
        dbHandler = new DBHandler(this);
        assignedCourses = new ArrayList<>();
        syncassignedCourses();
        syncassignedCoursesView();


        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToInstructorStarter();}
        });
        assignedCoursesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToAssignCourse(assignedCourses.get(position));
            }
        });

    }

    private void goToAssignCourse(Course selectedCourse) {
        Intent intent = new Intent(this, AssignCourse.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }
    private void goToInstructorStarter() {
        Intent intent = new Intent(this, InstructorStarter.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }

    private void syncassignedCourses() {
        assignedCourses.clear();
        Cursor cursor = dbHandler.getCourses();
        while (cursor.moveToNext()) {
            assignedCourses.add(new Course(Integer.valueOf(cursor.getInt(0)), cursor.getString(1), cursor.getString(2), Integer.valueOf(cursor.getString(3)), cursor.getString(4), cursor.getString(5), cursor.getString(6), Integer.valueOf(cursor.getString(7))));
        }
        cursor.close();

        getCourseCodesFromCourseList();
    }

    private void getCourseCodesFromCourseList() {
        String[] result = new String[assignedCourses.size()];
        int counter=0;
        for(Course c: assignedCourses) {
            result[counter] = c.getCode();
            counter++;
        }
        assignedcourseCodeStringList = result;
    }
    private void syncassignedCoursesView() {
        ArrayAdapter<String> usernameListAdapter = new ArrayAdapter<String>(this, R.layout.activity_simple_list_item2, R.id.textView27, assignedcourseCodeStringList);
        assignedCoursesView.setAdapter(usernameListAdapter);
    }



}