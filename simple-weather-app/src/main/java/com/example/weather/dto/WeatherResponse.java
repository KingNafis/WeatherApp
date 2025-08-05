package com.example.weather.dto;

public class WeatherResponse {
    private Weather[] weather;
    private Main main;
    private String name;

    public Weather[] getWeather() { return weather; }
    public void setWeather(Weather[] weather) { this.weather = weather; }
    public Main getMain() { return main; }
    public void setMain(Main main) { this.main = main; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public static class Weather {
        private String main;
        private String description;

        public String getMain() { return main; }
        public void setMain(String main) { this.main = main; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    public static class Main {
        private double temp;
        private double feels_like;

        public double getTemp() { return temp; }
        public void setTemp(double temp) { this.temp = temp; }
        public double getFeels_like() { return feels_like; }
        public void setFeels_like(double feels_like) { this.feels_like = feels_like; }
    }
}