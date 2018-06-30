package com.example.haako.practiceapplication;

import java.io.Serializable;

/**
 * Created by haako on 07.09.2016.
 */
public class ExchangeRates implements Serializable {

    private double EUR;
    private double GBP;
    private double USD;

    public double getEUR() { return EUR; }
    public void setEUR(double EUR) { this.EUR = EUR; }
    public double getGBP() { return GBP; }
    public void setGBP(double GBP) { this.GBP = GBP; }
    public double getUSD() { return USD; }
    public void setUSD(double USD) { this.USD = USD; }

}
