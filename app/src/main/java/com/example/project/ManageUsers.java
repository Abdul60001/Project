package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
public class ManageUsers extends AppCompatActivity {
    Button manage,back;
    ListView usersListView;

    ArrayList<User> usersList;
    DBHandler dbHandler;
    String[] usernameStringList;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        builder = new AlertDialog.Builder(this);

        manage = findViewById(R.id.button10);
        back = findViewById(R.id.button13);
        usersListView = findViewById(R.id.listview1);

        dbHandler = new DBHandler(this);
        usersList = new ArrayList<>();
        syncUserList();
        syncUsersListView();

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {goToAdminStarter();}
        });

        usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User selectedUser = usersList.get(position);
                builder.setMessage("Delete user " + selectedUser.getUsername() + "?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dbHandler.deleteUserById(selectedUser.getId());
                                syncUserList();
                                syncUsersListView();
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

    private void goToAdminStarter() {
        Intent intent = new Intent(this, AdminStarter.class);
        startActivity(intent);
    }

    private void syncUserList() {
        usersList.clear();
        Cursor cursor = dbHandler.getUsers();
        while (cursor.moveToNext()) {
            usersList.add(new User(Integer.valueOf(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        cursor.close();

        getUsernamesFromUserList();
    }

    private void getUsernamesFromUserList() {
        String[] result = new String[usersList.size()];
        int counter=0;
        for(User u: usersList) {
            result[counter] = u.getUsername();
            counter++;
        }
        usernameStringList = result;
    }

    private void syncUsersListView() {
        ArrayAdapter<String> usernameListAdapter = new ArrayAdapter<String>(this, R.layout.simple_list_item, R.id.textView10, usernameStringList);
        usersListView.setAdapter(usernameListAdapter);
    }

}