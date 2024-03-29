package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;

public class ViewEnrolledCoursesStudent extends AppCompatActivity {
    /**Class to view enrolled Courses by student */

    Button back;
    ListView enrolledCoursesView;
    ArrayList<Course> enrolledCourses;
    DBHandler dbHandler;
    String[] enrolledCourseCodeStringList;

    User currentUser;

    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewenrolled_courses_student);

        builder = new AlertDialog.Builder(this);

        back = findViewById(R.id.button50);
        enrolledCoursesView = findViewById(R.id.listview20);


        currentUser = (User) getIntent().getSerializableExtra("current_user");
        dbHandler = new DBHandler(this);
        enrolledCourses = new ArrayList<>();

        syncEnrolledCourses();
        syncCoursesListView();


        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToStudentStarter();
            }
        });

        enrolledCoursesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course selectedCourse = enrolledCourses.get(position);
                builder.setMessage("Un-enroll yourself from " + selectedCourse.getName() + "?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dbHandler.deleteEnrolment(currentUser.getId(), selectedCourse.getId());
                                syncEnrolledCourses();
                                syncCoursesListView();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {}
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });


    }

    private void goToStudentStarter() {
        Intent intent = new Intent(this, StudentStarter.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }

    private void syncEnrolledCourses(){
        enrolledCourses.clear();
        Cursor cursor = dbHandler.getEnrolledCoursesByUserId(currentUser.getId());
        ArrayList<Integer> courseIds = new ArrayList<Integer>();
        while(cursor.moveToNext()){
            courseIds.add(cursor.getInt(1));
        }

        for (int i : courseIds){
            Cursor c = dbHandler.getCoursesByCourseId(i);
            if (c.moveToNext()) {
                enrolledCourses.add(new Course(Integer.valueOf(c.getInt(0)), c.getString(1), c.getString(2), Integer.valueOf(c.getString(3)), c.getString(4), c.getString(5), c.getString(6), Integer.valueOf(c.getString(7))));
            }
            c.close();
        }
        cursor.close();

        getCourseCodesFromCourseList();
    }

    private void getCourseCodesFromCourseList() {
        String[] result = new String[enrolledCourses.size()];
        int counter=0;
        for(Course c: enrolledCourses) {
            result[counter] = c.getCode();
            counter++;
        }
        enrolledCourseCodeStringList = result;
    }

    private void syncCoursesListView(){
        ArrayAdapter<String> usernameListAdapter = new ArrayAdapter<String>(this, R.layout.simple_list_item, R.id.textView10, enrolledCourseCodeStringList);
        enrolledCoursesView.setAdapter(usernameListAdapter);
    }

}