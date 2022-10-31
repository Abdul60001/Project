package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManageStudents extends AppCompatActivity {
    Button delete,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_students);

        back = findViewById(R.id.button12);
        delete = findViewById(R.id.button11);

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToViewStudents();}
        });

    }

    private void goToViewStudents() {
        Intent intent = new Intent(this, ManageUsers.class);
        startActivity(intent);
    }
}