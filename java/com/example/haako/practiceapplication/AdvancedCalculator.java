package com.example.haako.practiceapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdvancedCalculator extends AppCompatActivity {

    private double rate;
    private DecimalFormat df = new DecimalFormat("#.00");
    private String[] fromArray = {"Select your currency", "NOK", "EUR", "USD", "GBP", "AUD", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "HKD", "HRK", "HUF", "IDR", "ILS", "INR", "JPY", "KRW", "MXN", "MYR", "NZD", "PHP", "PLN", "RON", "RUB", "SEK", "SGD", "THB", "TRY", "ZAR"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calculator);
        final Spinner fromSpinner = (Spinner) findViewById(R.id.fromSpinner);
        final ArrayAdapter<String> fromCurrency = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fromArray); //selected item will look like a spinner set from XML
        fromCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(fromCurrency);
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!fromSpinner.getSelectedItem().toString().equals("Select your currency")) {
                    populateToSpinner();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO - Implement useful solution here -.-
            }
        });
    }

    private void populateToSpinner() {
        Spinner fromSpinner = (Spinner) findViewById(R.id.fromSpinner);
        Spinner toSpinner = (Spinner) findViewById(R.id.toSpinner);
        ArrayList<String> toArray = new ArrayList<String>();
        toArray.add("Select desired currency");
        for (String s: fromArray) {
            if (!s.equals(fromSpinner.getSelectedItem().toString()) && !s.equals("Select your currency")) {
                toArray.add(s);
            }
        }
        ArrayAdapter<String> toCurrency = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, toArray);
        toSpinner.setAdapter(toCurrency);
        toSpinner.setVisibility(View.VISIBLE);
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            EditText input = (EditText) findViewById(R.id.inputAdv);
            Spinner toSpinner = (Spinner) findViewById(R.id.toSpinner);
            TextView output = (TextView) findViewById(R.id.outputAdv);

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!input.getText().toString().equals("")) {
                    if (!toSpinner.getSelectedItem().equals("Select desired currency")) {
                        getRates();
                    }
                    else {
                        output.setText("");
                    }
                }
                else {
                    output.setText("You must input a value");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getRates() {
        Spinner fromSpinner = (Spinner) findViewById(R.id.fromSpinner);
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, "http://api.fixer.io/latest?base="+fromSpinner.getSelectedItem().toString(), null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Spinner toSpinner = (Spinner) findViewById(R.id.toSpinner);
                        try {
                            JSONObject rates = response.getJSONObject("rates");
                            rate = rates.getDouble(toSpinner.getSelectedItem().toString());
                            printResult();
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

    private void printResult() {
        EditText input = (EditText) findViewById(R.id.inputAdv);
        TextView output = (TextView) findViewById(R.id.outputAdv);
        Spinner fromSpinner = (Spinner) findViewById(R.id.fromSpinner);
        Spinner toSpinner = (Spinner) findViewById(R.id.toSpinner);
        double in = Double.parseDouble(input.getText().toString());
        double out = in * rate;
        output.setText("" + df.format(in) + " " + fromSpinner.getSelectedItem().toString() + " equals " + df.format(out) +  " " + toSpinner.getSelectedItem().toString());
    }
}
