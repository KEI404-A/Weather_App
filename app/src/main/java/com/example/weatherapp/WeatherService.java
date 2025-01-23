package com.example.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("forecast.json")
    Call<WeatherResponse> getWeather(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("exclude") String exclude,
            @Query("units") String units,
            @Query("appid") String apiKey,
            @Query("days") int days
    );
}
