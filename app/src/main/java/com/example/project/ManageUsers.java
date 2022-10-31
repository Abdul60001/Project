package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.ListView;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
public class ManageUsers extends AppCompatActivity {
    Button manage,back;
    ListView usersListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);
        manage = findViewById(R.id.button10);
        back = findViewById(R.id.button13);
        usersListView = findViewById(R.id.listview1);

        ArrayList<String> users = new ArrayList<>();

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToAdminStarter();}
        });

    }

    private void goToAdminStarter() {
        Intent intent = new Intent(this, AdminStarter.class);
        startActivity(intent);
    }


}