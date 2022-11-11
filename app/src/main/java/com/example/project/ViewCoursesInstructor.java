package com.example.project;

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

    ArrayList<Course> coursesList2;
    DBHandler dbHandler;
    String[] courseCodeStringList;

    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course_insturctor);

        back = findViewById(R.id.button32);
        coursesListView = findViewById(R.id.ListView3);

        currentUser = (User) getIntent().getSerializableExtra("current_user");

        dbHandler = new DBHandler(this);
        coursesList2 = new ArrayList<>();
        syncCourseList();
        syncCoursesListView();

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToInstructorStarter();}
        });

    }

    private void goToInstructorStarter() {
        Intent intent = new Intent(this,InstructorStarter.class);
        startActivity(intent);
    }
    private void goToEditCourse(Course selectedCourse) {
        Intent intent = new Intent(this, EditCourses.class);
        intent.putExtra("current_user", currentUser);
        intent.putExtra("selected_course", selectedCourse);
        startActivity(intent);
    }
    private void goToAssignCourse(Course selectedCourse) {
        Intent intent = new Intent(this, AssignCourse.class);
        intent.putExtra("current_user", currentUser);
        intent.putExtra("selected_course", selectedCourse);
        startActivity(intent);
    }

    private void syncCourseList() {
        coursesList2.clear();
        Cursor cursor = dbHandler.getCourses();
        while (cursor.moveToNext()) {
            coursesList2.add(new Course(Integer.valueOf(cursor.getInt(0)), cursor.getString(1), cursor.getString(2), Integer.valueOf(cursor.getString(3)), cursor.getString(4), Integer.valueOf(cursor.getString(5))));
        }
        cursor.close();

        getCourseCodesFromCourseList();
    }

    private void getCourseCodesFromCourseList() {
        String[] result = new String[coursesList2.size()];
        int counter=0;
        for(Course c: coursesList2) {
            result[counter] = c.getCode();
            counter++;
        }
        courseCodeStringList = result;
    }

    private void syncCoursesListView() {
        ArrayAdapter<String> usernameListAdapter = new ArrayAdapter<String>(this, R.layout.simple_list_item, R.id.textView10, courseCodeStringList);
        coursesListView.setAdapter(usernameListAdapter);
    }

}
