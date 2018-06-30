package com.example.haako.practiceapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class Menu extends AppCompatActivity {

    private ExchangeRates exchangeRates = new ExchangeRates();
    private Users userLibrary;
    private User loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
        Boolean disabled = prefs.getBoolean("showUserMenu", false);
        setContentView(R.layout.activity_menu);
        getRates();
        loading();
        if (getIntent().getSerializableExtra("UserLibrary") != null) {
            userLibrary = (Users) getIntent().getSerializableExtra("UserLibrary");
        }
        else {
            userLibrary = new Users();
        }
        if (getIntent().getSerializableExtra("User") != null) {
            loggedIn = (User) getIntent().getSerializableExtra("User");
            Button logIn = (Button) findViewById(R.id.logInBtn);
            logIn.setVisibility(View.GONE);
            Button logOut = (Button) findViewById(R.id.logOutBtn);
            logOut.setVisibility(View.VISIBLE);
        }
        if (disabled) {
            TextView t = (TextView) findViewById(R.id.usersHeader);
            t.setVisibility(View.INVISIBLE);
            Button b1 = (Button) findViewById(R.id.logInBtn);
            b1.setVisibility(View.INVISIBLE);
            Button b2 = (Button)findViewById(R.id.registerUserBtn);
            b2.setVisibility(View.INVISIBLE);
        }
    }

    public void basicBtn_click(View v) {
        Intent intent = new Intent(this, BasicCalculator.class);
        intent.putExtra("ExchangeRates", exchangeRates);
        startActivity(intent);
    }

    public void intermediateBtn_click(View v) {
        Intent intent = new Intent(this, IntermediateCalculator.class);
        intent.putExtra("ExchangeRates", exchangeRates);
        startActivity(intent);
    }

    public void advancedBtn_click(View v) {
        Intent intent = new Intent(this, AdvancedCalculator.class);
        startActivity(intent);
    }

    public void registerUserBtn_click(View v) {
        Intent intent = new Intent(this, RegisterUser.class);
        intent.putExtra("UserLibrary", userLibrary);
        startActivity(intent);
    }

    public void logInBtn_click(View v) {
        Intent intent = new Intent(this, LogIn.class);
        intent.putExtra("UserLibrary", userLibrary);
        startActivity(intent);
    }

    public void logOutBtn_click(View v) {
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra("UserLibrary", userLibrary);
        startActivity(intent);
    }

    private void loading() {
        AlphaAnimation alpha = new AlphaAnimation(0.0f, 1.0f);
        alpha.setDuration(1000); // Make animation instant
        alpha.setFillAfter(true); // Tell it to persist after the animation ends
        RelativeLayout menu = (RelativeLayout) findViewById(R.id.mainMenu);
        menu.startAnimation(alpha);
    }

    public void prefsBtn_click(View v) {
        Intent intent = new Intent(this, Preferences.class);
        startActivity(intent);
    }

    private void getRates() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, "http://api.fixer.io/latest?base=NOK", null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject rates = response.getJSONObject("rates");
                            exchangeRates.setEUR(rates.getDouble("EUR"));
                            exchangeRates.setGBP(rates.getDouble("GBP"));
                            exchangeRates.setUSD(rates.getDouble("USD"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        queue.add(jsObjRequest);
    }
}
