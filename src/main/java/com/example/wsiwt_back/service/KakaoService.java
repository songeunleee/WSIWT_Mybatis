package com.example.wsiwt_back.service;

import com.google.common.net.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class KakaoService {

    @Value("${kakao_key}")
    private String kakao_key;

    private final WebClient webClient;

    public KakaoService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://dapi.kakao.com/v2/local/geo").build();
    }

    public Mono<String> getRegion(double longitude, double latitude) {
        return webClient.get()
                .uri("/coord2regioncode.json?x={longitude}&y={latitude}", longitude, latitude)
                .header(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakao_key)
                .retrieve()
                .bodyToMono(String.class);
    }

}
