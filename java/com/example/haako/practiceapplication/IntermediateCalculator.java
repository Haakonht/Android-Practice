package com.example.haako.practiceapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class IntermediateCalculator extends AppCompatActivity {

    private DecimalFormat df = new DecimalFormat("#.00");
    private ExchangeRates exchangeRates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate_calculator);
        exchangeRates = (ExchangeRates) getIntent().getSerializableExtra("ExchangeRates");
    }

    public void radio_click(View v) {
        EditText input = (EditText) findViewById(R.id.inputInt);
        TextView output = (TextView) findViewById(R.id.outputInt);
        RadioButton eurBtn = (RadioButton) findViewById(R.id.eurRadio);
        RadioButton gbpBtn = (RadioButton) findViewById(R.id.gbpRadio);
        RadioButton usdBtn = (RadioButton) findViewById(R.id.usdRadio);
        if (!input.getText().toString().equals("")) {
            double in = Double.parseDouble(input.getText().toString());
            if (eurBtn.isChecked()) {
                output.setText("" + df.format(in) + " NOK equals " + df.format(in * exchangeRates.getEUR()) + " EUR");
            }
            else if (gbpBtn.isChecked()) {
                output.setText("" + df.format(in) + " NOK equals " + df.format(in * exchangeRates.getGBP()) + " EUR");
            }
            else if (usdBtn.isChecked()) {
                output.setText("" + df.format(in) + " NOK equals " + df.format(in * exchangeRates.getUSD()) + " EUR");
            }
        }
        else {
            output.setText("You have to set a value");
        }
    }
}
