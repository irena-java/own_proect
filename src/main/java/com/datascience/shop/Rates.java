package com.datascience.shop;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {

    @JsonProperty("cc")
    private String currencyName;

    @JsonProperty("rate")
    private double currencyRate;

    @JsonCreator
    public Rates(@JsonProperty("cc") String currencyName, @JsonProperty("rate") double currencyRate) {
        this.currencyName = currencyName;
        this.currencyRate = currencyRate;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(double currencyRate) {
        this.currencyRate = currencyRate;
    }

    @Override
    public String toString() {
        return "Rates{" +
                "currencyName='" + currencyName + '\'' +
                ", currencyRate=" + currencyRate +
                '}';
    }
}
