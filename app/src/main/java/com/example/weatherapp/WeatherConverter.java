package com.example.weatherapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WeatherConverter {

    public static List<WeatherHour> convertHourlyData(List<WeatherResponse.Hourly> hourlyList) {
        List<WeatherHour> result = new ArrayList<>();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        for (WeatherResponse.Hourly hourly : hourlyList) {
            String time = timeFormat.format(new Date(hourly.getDt() * 1000L));

            // Create new WeatherHour with available data
            WeatherHour weatherHour = new WeatherHour(
                    time,                   // Waktu yang sudah diformat
                    hourly.getTemp(),       // Suhu
                    hourly.getWeatherDesc(),// Deskripsi cuaca
                    "",                     // Icon (kosong karena tidak ada di response)
                    0,                      // Humidity (default 0 karena tidak ada di response)
                    0.0                     // Wind speed (default 0 karena tidak ada di response)
            );

            result.add(weatherHour);
        }
        return result;
    }

    public static List<WeatherDay> convertDailyData(List<WeatherResponse.Daily> dailyList) {
        List<WeatherDay> result = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM", Locale.getDefault());

        for (WeatherResponse.Daily daily : dailyList) {
            String date = dateFormat.format(new Date(daily.getDt() * 1000L));

            WeatherDay weatherDay = new WeatherDay(
                    date,                    // Tanggal yang sudah diformat
                    daily.getTemp().getDay(),// Suhu siang hari sebagai suhu utama
                    daily.getTemp().getNight(), // Suhu malam sebagai suhu minimum
                    daily.getTemp().getDay(),   // Suhu siang sebagai suhu maximum
                    daily.getWeatherDesc(),     // Deskripsi cuaca
                    ""
            );

            result.add(weatherDay);
        }
        return result;
    }
}