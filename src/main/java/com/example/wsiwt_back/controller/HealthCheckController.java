package com.example.wsiwt_back.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/")
    public String healthCheck(){
        return "The wsiwt service with docker githubaction eb iam  is up and running... 진짜 진짜로 마지막 github action배포 openApiController 추가 cloudinary 옮김 myootds api 추가";
    }
}
