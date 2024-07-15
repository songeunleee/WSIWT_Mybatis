package com.example.wsiwt_back.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherService {

@Value("${weather_apikey}")
    private String weather_api_key;

    private final WebClient webClient;

    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0").build();
    }

    public String getWeatherForecast(  String base_date,
                                           String base_time, int nx, int ny) {
         return webClient.get()
                .uri("/getVilageFcst?pageNo={pageNo}&numOfRows={numOfRows}&dataType={dataType}&base_date={base_date}&base_time={base_time}&nx={nx}&ny={ny}&serviceKey={apiKey}",
                        1, 1000, "JSON", base_date, base_time, nx, ny, weather_api_key)
                .retrieve().bodyToMono(String.class).block();
    }

}
