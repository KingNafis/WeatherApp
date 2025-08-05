package com.example.weather.controller;

import com.example.weather.dto.WeatherResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherController {

    private final String WEATHER_URL =
            "https://api.openweathermap.org/data/2.5/weather?q={city}&appid=2de65fb0af63548b95aff885185ec0df&units=metric";

    @GetMapping("/")
    public String showWeatherForm(Model model) {
        return "weather";
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam String city, Model model) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            WeatherResponse response = restTemplate.getForObject(
                    WEATHER_URL,
                    WeatherResponse.class,
                    city
            );

            if (response != null) {
                model.addAttribute("weather", response);
                model.addAttribute("advice", generateAdvice(response));
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching weather data: " + e.getMessage());
        }
        model.addAttribute("city", city);
        return "weather";
    }

    private String generateAdvice(WeatherResponse weather) {
        String mainCondition = weather.getWeather()[0].getMain().toLowerCase();
        double temp = weather.getMain().getTemp();

        if (mainCondition.contains("rain")) {
            return "Don't forget your umbrella! It's raining outside.";
        } else if (mainCondition.contains("cloud")) {
            return "The weather is cloudy today. Might be a bit gloomy.";
        } else if (mainCondition.contains("sun") || mainCondition.contains("clear")) {
            if (temp > 25) {
                return "It's hot and sunny! Wear sunscreen, sunglasses, and stay hydrated!";
            } else {
                return "It's sunny outside! Don't forget your sunglasses.";
            }
        } else if (mainCondition.contains("snow")) {
            return "It's snowing! Wear warm clothes and boots.";
        } else if (temp < 10) {
            return "Brrr! It's cold outside. Wear a heavy jacket!";
        } else if (temp > 30) {
            return "Heat warning! Stay in the shade and drink plenty of water.";
        }
        return "Enjoy your day!";
    }
}