package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText usernameInput, passwordInput;
    Button login, createAccount;
    ArrayList<User> userList;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(this);

        usernameInput = findViewById(R.id.editTextTextEmailAddress);
        passwordInput = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.button2);
        createAccount = findViewById(R.id.button);

        userList = new ArrayList<>();
        syncUserList();

        //Create Admin User
        dbHandler.addUser(new User(1, "admin", "admin123", "Admin"));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                syncUserList();
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("test2");
            }
        });
    }

    private void syncUserList() {
        userList.clear();
        Cursor cursor = dbHandler.getUsers();
        while (cursor.moveToNext()) {
            userList.add(new User(Integer.valueOf(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        cursor.close();
    }


}