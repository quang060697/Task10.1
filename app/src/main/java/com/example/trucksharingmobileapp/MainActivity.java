package com.example.trucksharingmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText userNameInput = findViewById(R.id.userNameInput);
        EditText passwordInput = findViewById(R.id.passwordInput);

        Button loginButton = findViewById(R.id.loginButton);
        Button signUpButton = findViewById(R.id.signUpButton);

        db = new DatabaseHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userNameInput.getText().toString();
                String password = passwordInput.getText().toString();



                boolean res =  db.fetchUser(username,password);
               if (res)
               {
                   Intent intent1 = new Intent(getApplicationContext(),HomeActivity.class);
                   intent1.putExtra("username",username);
                   startActivity(intent1);
                   finish();
               }
               else
               {
                   Toast.makeText(MainActivity.this, "Wrong user name or password", Toast.LENGTH_SHORT).show();

               }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);

                startActivity(intent);
                finish();
            }
        });
    }
}