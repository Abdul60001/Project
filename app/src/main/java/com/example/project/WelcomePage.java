package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity {

    TextView greeting, userRole;
    Button continueButton;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page2);

        greeting = findViewById(R.id.textView5);
        userRole = findViewById(R.id.textView3);
        continueButton = findViewById(R.id.button3);

        currentUser = (User) getIntent().getSerializableExtra("current_user");

        greeting.setText("Welcome " + currentUser.getUsername() + "!");
        userRole.setText("User role: " + currentUser.getUserType());

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentUser.getUserType().equals("admin")) {
                    goToAdminStarter();
                }
            }
        });
    }

    private void goToAdminStarter() {
        Intent intent = new Intent(this, AdminStarter.class);
        startActivity(intent);
    }
}