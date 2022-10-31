package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ListView;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
public class ManageCourses extends AppCompatActivity {
    Button back,manage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_courses);

        back = findViewById(R.id.button20);
        manage = findViewById(R.id.button21);

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToViewCourses();}
        });
    }

    private void goToViewCourses() {
        Intent intent = new Intent(this, ViewCourses.class);
        startActivity(intent);
    }
}