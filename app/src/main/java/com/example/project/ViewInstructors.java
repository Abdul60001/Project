package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.ListView;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
public class ViewInstructors extends AppCompatActivity {
    Button manage,back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_instructors);

        manage = findViewById(R.id.button15);
        back = findViewById(R.id.button14);

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToViewUsersStarter();}
        });

        manage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToManageInstructors();}
        });

    }

    private void goToViewUsersStarter() {
        Intent intent = new Intent(this, ViewUsersStarter.class);
        startActivity(intent);
    }

    private void goToManageInstructors() {
        Intent intent = new Intent(this, ManageInstructors.class);
        startActivity(intent);
    }
}