package com.example.haako.practiceapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class BasicCalculator extends AppCompatActivity {

    private DecimalFormat df = new DecimalFormat("#.00");
    private ExchangeRates exchangeRates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_calculator);
        exchangeRates = (ExchangeRates) getIntent().getSerializableExtra("ExchangeRates");
    }

    public void eurBtn_click(View v) {
        EditText input = (EditText) findViewById(R.id.inputBasic);
        TextView output = (TextView) findViewById(R.id.outputBasic);
        if (!input.getText().toString().equals("")){
            double in = Double.parseDouble(input.getText().toString());
            output.setText("" + df.format(in) + " NOK equals " + df.format(in * exchangeRates.getEUR()) + " EUR");
        }
        else {
            output.setText("You have to input a value");
        }
    }

    public void gbpBtn_click(View v) {
        EditText input = (EditText) findViewById(R.id.inputBasic);
        TextView output = (TextView) findViewById(R.id.outputBasic);
        if (!input.getText().toString().equals("")){
            double in = Double.parseDouble(input.getText().toString());
            output.setText("" + df.format(in) + " NOK equals " + df.format(in * exchangeRates.getGBP()) + " GBP");
        }
        else {
            output.setText("You have to input a value");
        }
    }

    public void usdBtn_click(View v) {
        EditText input = (EditText) findViewById(R.id.inputBasic);
        TextView output = (TextView) findViewById(R.id.outputBasic);
        if (!input.getText().toString().equals("")){
            double in = Double.parseDouble(input.getText().toString());
            output.setText("" + df.format(in) + " NOK equals " + df.format(in * exchangeRates.getUSD()) + " USD");
        }
        else {
            output.setText("You have to input a value");
        }
    }
}
