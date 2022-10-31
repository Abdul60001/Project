package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.ListView;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
public class ViewCourses extends AppCompatActivity {

    Button manage,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courses);

        manage = findViewById(R.id.button18);
        back = findViewById(R.id.button19);

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToAdminStarter();}
        });

        manage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToManageCourses();}
        });

    }

    private void goToAdminStarter() {
        Intent intent = new Intent(this,AdminStarter.class);
        startActivity(intent);
    }

    private void goToManageCourses() {
        Intent intent = new Intent(this, ManageCourses.class);
        startActivity(intent);
    }
}