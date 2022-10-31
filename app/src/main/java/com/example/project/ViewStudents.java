package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.ListView;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.database.Cursor;

import java.util.ArrayList;
public class ViewStudents extends AppCompatActivity {
    Button manage,back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);
        manage = findViewById(R.id.button10);
        back = findViewById(R.id.button13);
        ListView listView = (ListView) findViewById(R.id.listview1);
        ArrayList<String> users = new ArrayList<>();

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToViewUsersStarter();}
        });
    }

    private void goToViewUsersStarter() {
        Intent intent = new Intent(this, ViewUsersStarter.class);
        startActivity(intent);
    }
}