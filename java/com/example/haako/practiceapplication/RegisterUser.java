package com.example.haako.practiceapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterUser extends AppCompatActivity {

    private Users userLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        userLibrary = (Users) getIntent().getSerializableExtra("UserLibrary");
    }

    public void registerBtn_click(View v) {
        EditText username = (EditText) findViewById(R.id.usernameInput);
        EditText password = (EditText) findViewById(R.id.passwordInput);
        EditText passwordConfirm = (EditText) findViewById(R.id.passwordConfirmation);
        if (!username.getText().toString().equals("")) {
            if (!password.getText().toString().equals("")) {
                if (password.getText().toString().equals(passwordConfirm.getText().toString())) {
                    userLibrary.addUser(new User(username.getText().toString(), password.getText().toString()));

                    Intent intent = new Intent(this, Menu.class);
                    intent.putExtra("UserLibrary", userLibrary);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Your passwords do not match", Toast.LENGTH_SHORT).show();
                }

            }
            else {
                Toast.makeText(getApplicationContext(), "You must input a password", Toast.LENGTH_SHORT).show();
            }

        }
        else {
            Toast.makeText(getApplicationContext(), "You must input a username", Toast.LENGTH_SHORT).show();
        }
    }
}
