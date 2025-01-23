package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

public class CurrentWeather {
    @SerializedName("temp")
    private double temp;

    @SerializedName("weather_desc")
    private String weatherDesc;

    // Getters
    public double getTemp() {
        return temp;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }
}