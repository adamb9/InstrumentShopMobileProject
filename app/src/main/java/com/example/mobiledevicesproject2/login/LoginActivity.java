package com.example.mobiledevicesproject2.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobiledevicesproject2.DBHelper;
import com.example.mobiledevicesproject2.R;
import com.example.mobiledevicesproject2.home.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    String correctPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DBHelper db = new DBHelper(LoginActivity.this);
        EditText usernameText = (EditText) findViewById(R.id.usernameInput);
        EditText passwordText = (EditText) findViewById(R.id.passwordInput);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        Button registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(register);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                ArrayList<HashMap<String, String>> userInfo = db.getUserByUsername(username);

                if(userInfo.size() > 0 ) {
                    HashMap<String, String> hashmap = userInfo.get(0);
                    correctPassword = hashmap.get("password");
                    if(password.equals(correctPassword)){
                        Toast.makeText(getApplicationContext(), "Successful Sign-in!", Toast.LENGTH_SHORT).show();
                        Intent home = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(home);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Username and password do not match!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Username not found!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sign_in:
                return true;
            case R.id.action_basket:
                // do your code
                return true;
            case R.id.action_home:
                Intent home = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(home);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}