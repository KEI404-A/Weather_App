package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

public class WeatherHour {
    @SerializedName("time")
    private String time;

    @SerializedName("temp")
    private double temp;

    @SerializedName("weather_desc")
    private String weatherDesc;

    @SerializedName("icon")
    private String icon;

    @SerializedName("humidity")
    private int humidity;

    @SerializedName("wind_speed")
    private double windSpeed;

    // Constructor
    public WeatherHour(String time, double temp, String weatherDesc, String icon,
                       int humidity, double windSpeed) {
        this.time = time;
        this.temp = temp;
        this.weatherDesc = weatherDesc;
        this.icon = icon;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    // Getters
    public String getTime() {
        return time;
    }

    public double getTemp() {
        return temp;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public String getIcon() {
        return icon;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    // Setters
    public void setTime(String time) {
        this.time = time;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
}