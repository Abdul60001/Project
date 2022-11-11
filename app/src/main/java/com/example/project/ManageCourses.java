package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.view.View;

import java.util.ArrayList;

public class ManageCourses extends AppCompatActivity {
    Button back, newCourse;
    ListView coursesListView;

    ArrayList<Course> coursesList;
    DBHandler dbHandler;
    String[] courseCodeStringList;

    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_courses);

        back = findViewById(R.id.button20);
        newCourse = findViewById(R.id.button27);
        coursesListView = findViewById(R.id.listview2);

        currentUser = (User) getIntent().getSerializableExtra("current_user");

        dbHandler = new DBHandler(this);
        coursesList = new ArrayList<>();
        syncCourseList();
        syncCoursesListView();

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToAdminStarter();}
        });

        newCourse.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                goToEditCourse();
            }
        });

        coursesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToEditCourse(coursesList.get(position));
            }
        });
    }

    private void goToEditCourse(Course selectedCourse) {
        Intent intent = new Intent(this, EditCourses.class);
        intent.putExtra("current_user", currentUser);
        intent.putExtra("selected_course", selectedCourse);
        startActivity(intent);
    }

    private void goToEditCourse() {
        Intent intent = new Intent(this, EditCourses.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }

    private void goToAdminStarter() {
        Intent intent = new Intent(this, AdminStarter.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }

    private void syncCourseList() {
        coursesList.clear();
        Cursor cursor = dbHandler.getCourses();
        while (cursor.moveToNext()) {
            coursesList.add(new Course(Integer.valueOf(cursor.getInt(0)), cursor.getString(1), cursor.getString(2), Integer.valueOf(cursor.getString(3)), cursor.getString(4), Integer.valueOf(cursor.getString(5))));
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
}