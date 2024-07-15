package com.example.wsiwt_back.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CityService {

    @Value("${city_key}")
    private String city_key;

    private final WebClient webClient;

    public CityService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://openapi.seoul.go.kr:8088/").build();
    }

    public String getCityData(  String cityName) {
        return webClient.get()
                .uri(city_key+"/json/citydata/1/5/"+cityName)
                .retrieve().bodyToMono(String.class).block();
    }
}
