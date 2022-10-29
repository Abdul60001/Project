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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page2);

        greeting = findViewById(R.id.textView5);
        userRole = findViewById(R.id.textView3);
        continueButton = findViewById(R.id.button3);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAdminStarter();
            }
        });
    }

    private void goToAdminStarter() {
        Intent intent = new Intent(this, AdminStarter.class);
        startActivity(intent);
    }
}