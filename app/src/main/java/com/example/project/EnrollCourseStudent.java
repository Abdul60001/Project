package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


public class EnrollCourseStudent extends AppCompatActivity {
    /** Enroll class by student */
    Button back;
    User currentUser;
    ArrayList<Course> coursesList;
    ListView coursesListView;
    DBHandler dbHandler;
    String[] courseCodeStringList;
    ArrayList<Course> enrolledCourses;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_course_student);
        coursesList = new ArrayList<Course>();
        back = findViewById(R.id.button51);
        coursesListView = findViewById(R.id.courseList);

        dbHandler = new DBHandler(this);
        currentUser = (User) getIntent().getSerializableExtra("current_user");
        coursesList = (ArrayList<Course>) getIntent().getSerializableExtra("course_search");

        enrolledCourses = new ArrayList<>();

        builder = new AlertDialog.Builder(this);

        getCourseCodesFromCourseList();
        syncCoursesListView();
        syncEnrolledCourses();

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){goToSearchCourse();}
        } );

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
                            "Instructor: "+""+"\n"+
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
        builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                })
                .setPositiveButton("Enrol", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        if(checkTimeConflict(course)) {
                            displayDialogWithMessage("This course conflicts with one of your other courses.");
                        }
                        else {
                            dbHandler.addEnrolment(currentUser.getId(), course.getId());
                            goToViewEnrolledCourses();
                        }
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void displayDialogWithMessage(String message) {
        builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                });
        AlertDialog alert = builder.create();
        alert.show();
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
    }

    private boolean checkTimeConflict(Course newCourse) {
        for(Course c: enrolledCourses) {
            if(c.getCourseDay().equalsIgnoreCase(newCourse.getCourseDay()) && c.getCourseHours().equals(newCourse.getCourseHours())) {
                return true;
            }
        }
        return false;
    }

    private void goToSearchCourse(){
        Intent intent = new Intent(this, StudentSearchCourse.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }

    private void goToViewEnrolledCourses(){
        Intent intent = new Intent(this, ViewEnrolledCoursesStudent.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
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