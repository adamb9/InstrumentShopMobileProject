package com.example.mobiledevicesproject2.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobiledevicesproject2.DBHelper;
import com.example.mobiledevicesproject2.R;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        DBHelper db = new DBHelper(RegistrationActivity.this);
        EditText usernameEditText = (EditText) findViewById(R.id.usernameRegistration);
        EditText passwordEditText = (EditText) findViewById(R.id.passwordRegistration);
        EditText addressEditText = (EditText) findViewById(R.id.addressRegistration);
        Button registerAccountButton = (Button) findViewById(R.id.registerAccountButton);

        registerAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String address = addressEditText.getText().toString().trim();
                db.addUser(username, password, address);
                Toast.makeText(getApplicationContext(), "Account Created Successfully", Toast.LENGTH_SHORT).show();
                Intent toLogin = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(toLogin);
            }
        });
    }
}