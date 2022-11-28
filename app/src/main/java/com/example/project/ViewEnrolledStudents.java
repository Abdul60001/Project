package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewEnrolledStudents extends AppCompatActivity {

    Button back;
    ListView studentsListView;

    ArrayList<User> studentsList;
    DBHandler dbHandler;
    String[] studentsStringList;

    User currentUser;
    Course selectedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_enrolled_students);

        back = findViewById(R.id.button52);
        studentsListView = findViewById(R.id.ListView300);

        currentUser = (User) getIntent().getSerializableExtra("current_user");
        selectedCourse = (Course) getIntent().getSerializableExtra("selected_course");

        syncStudents();
        syncCoursesListView();

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToViewCoursesInstructor();}
        });
    }

    private void goToViewCoursesInstructor() {
        Intent intent = new Intent(this,ViewCoursesInstructor.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }

    private void syncStudents(){
        studentsList.clear();
        Cursor cursor = dbHandler.getEnrolledUsersByCourseId(selectedCourse.getId());
        ArrayList<Integer> userIds = new ArrayList<Integer>();
        while(cursor.moveToNext()){
            userIds.add(cursor.getInt(0));
        }

        for (int i : userIds){
            Cursor c = dbHandler.getUsersById(i);
            if (c.moveToNext()) {
                studentsList.add(new User(Integer.valueOf(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            }
            c.close();
        }
        cursor.close();

        getUsernamesFromStudentsList();
    }

    private void getUsernamesFromStudentsList() {
        String[] result = new String[studentsList.size()];
        int counter=0;
        for(User u: studentsList) {
            result[counter] = u.getUsername();
            counter++;
        }
        studentsStringList = result;
    }

    private void syncCoursesListView() {
        ArrayAdapter<String> usernameListAdapter = new ArrayAdapter<String>(this, R.layout.simple_list_item, R.id.textView10, studentsStringList);
        studentsListView.setAdapter(usernameListAdapter);
    }
}