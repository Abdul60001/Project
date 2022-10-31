package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
public class ManageUsers extends AppCompatActivity {
    Button manage,back;
    ListView usersListView;

    ArrayList<User> userList;
    DBHandler dbHandler;
    String[] usernameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);
        manage = findViewById(R.id.button10);
        back = findViewById(R.id.button13);
        usersListView = findViewById(R.id.listview1);

        dbHandler = new DBHandler(this);
        userList = new ArrayList<>();
        syncUserList();
        usernameList = getUsernamesFromUserList();

        ArrayAdapter<String> usernameListAdapter = new ArrayAdapter<String>(this, R.layout.simple_list_item, R.id.textView10, usernameList);
        usersListView.setAdapter(usernameListAdapter);

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToAdminStarter();}
        });

    }

    private void goToAdminStarter() {
        Intent intent = new Intent(this, AdminStarter.class);
        startActivity(intent);
    }

    private void syncUserList() {
        userList.clear();
        Cursor cursor = dbHandler.getUsers();
        while (cursor.moveToNext()) {
            userList.add(new User(Integer.valueOf(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        cursor.close();
    }

    private String[] getUsernamesFromUserList() {
        String[] result = new String[userList.size()];
        int counter=0;
        for(User u: userList) {
            result[counter] = u.getUsername();
            counter++;
        }
        return result;
    }

}