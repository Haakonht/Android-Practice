package com.example.haako.practiceapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Preferences extends AppCompatActivity {

    Button b;
    CheckBox cb;
    Boolean disableMenu = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        SharedPreferences prefs = getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
        Boolean disabled = prefs.getBoolean("showUserMenu", false);
        if (disabled != null) {
            disableMenu = disabled;
        }
    }

    public void backBtn_click(View v) {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    public void disableCheck_click(View v) {
        CheckBox cb = (CheckBox) findViewById(R.id.disableCheck);
        if (cb.isChecked()) {
            disableMenu = true;
        }
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
        editor.putBoolean("showUserMenu", disableMenu);
        editor.commit();
    }
}
