package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherResponse {

    @SerializedName("current")
    private Current current;

    @SerializedName("hourly")
    private List<Hourly> hourly;

    @SerializedName("daily")
    private List<Daily> daily;

    @SerializedName("timezone")
    private String timezone;

    public Current getCurrent() {
        return current;
    }

    public List<Hourly> getHourly() {
        return hourly;
    }

    public List<Daily> getDaily() {
        return daily;
    }

    public String getTimezone() {
        return timezone;
    }

    public static class Current {
        @SerializedName("temp")
        private double temp;

        @SerializedName("weather")
        private List<WeatherDescription> weather;

        public double getTemp() {
            return temp;
        }

        public String getWeatherDesc() {
            return weather.get(0).description;
        }
    }

    public static class WeatherDescription {
        @SerializedName("description")
        private String description;
    }

    public static class Hourly {
        @SerializedName("dt")
        private long dt;

        @SerializedName("temp")
        private double temp;

        @SerializedName("weather")
        private List<WeatherDescription> weather;

        public long getDt() {
            return dt;
        }

        public double getTemp() {
            return temp;
        }

        public String getWeatherDesc() {
            return weather.get(0).description;
        }
    }

    public static class Daily {
        @SerializedName("dt")
        private long dt;

        @SerializedName("temp")
        private Temp temp;

        @SerializedName("weather")
        private List<WeatherDescription> weather;

        public long getDt() {
            return dt;
        }

        public Temp getTemp() {
            return temp;
        }

        public String getWeatherDesc() {
            return weather.get(0).description;
        }

        public static class Temp {
            @SerializedName("day")
            private double day;

            @SerializedName("night")
            private double night;

            public double getDay() {
                return day;
            }

            public double getNight() {
                return night;
            }
        }
    }
}
