package com.example.haako.practiceapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    private Users userLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        userLibrary = (Users) getIntent().getSerializableExtra("UserLibrary");
    }

    public void LogInUserBtn_click(View v) {
        EditText username = (EditText) findViewById(R.id.usernameLogIn);
        EditText password = (EditText) findViewById(R.id.passwordLogIn);

        User u = userLibrary.findUser(username.getText().toString());
        if (u.getUsername().equals(username.getText().toString()) && u.getPassword().equals(password.getText().toString())) {
            Intent intent = new Intent(this, Menu.class);
            intent.putExtra("UserLibrary", userLibrary);
            intent.putExtra("User", u);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(), "Incorrect username / password", Toast.LENGTH_SHORT).show();
        }
    }
}
