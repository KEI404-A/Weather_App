package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

public class WeatherDay {
    @SerializedName("date")
    private String date;

    @SerializedName("temp")
    private double temp;

    @SerializedName("temp_min")
    private double tempMin;

    @SerializedName("temp_max")
    private double tempMax;

    @SerializedName("weather_desc")
    private String weatherDesc;

    @SerializedName("icon")
    private String icon;

    // Constructor
    public WeatherDay(String date, double temp, double tempMin, double tempMax, String weatherDesc, String icon) {
        this.date = date;
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.weatherDesc = weatherDesc;
        this.icon = icon;
    }

    // Getters
    public String getDate() {
        return date;
    }

    public double getTemp() {
        return temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public String getIcon() {
        return icon;
    }

    // Setters if needed
    public void setDate(String date) {
        this.date = date;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}