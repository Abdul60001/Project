package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.ListView;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
public class ManageInstructors extends AppCompatActivity {
    Button delete,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_instructors);

        back = findViewById(R.id.button16);

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToViewInstructors();}
        });
    }

    private void goToViewInstructors() {
        Intent intent = new Intent(this, ViewInstructors.class);
        startActivity(intent);
    }
}