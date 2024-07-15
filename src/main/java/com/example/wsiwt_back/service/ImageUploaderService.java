package com.example.wsiwt_back.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ImageUploaderService {

    @Value("${cloudinary_preset}")
    private String cloudinary_preset;

    private final WebClient webClient;

    public ImageUploaderService(WebClient.Builder webClientBuilder,@Value("${cloudinary_url}") String cloudinary_url ) {
        this.webClient = webClientBuilder.baseUrl(cloudinary_url).build();
    }


    public Mono<String> getUrl(MultipartFile file){
        return webClient.post()
                .body(BodyInserters.fromMultipartData("file", file.getResource())
                        .with("upload_preset", cloudinary_preset))
                .retrieve()
                .bodyToMono(String.class);
    }


}
