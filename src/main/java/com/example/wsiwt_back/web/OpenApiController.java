package com.example.wsiwt_back.web;

import com.example.wsiwt_back.service.CityService;
import com.example.wsiwt_back.service.ImageUploaderService;
import com.example.wsiwt_back.service.KakaoService;
import com.example.wsiwt_back.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Api( tags = {"openAPI"})
@Tag(name = "openAPI", description = "OpenAPI와 관련한 컨트롤러")
@RequiredArgsConstructor
@RestController
@Slf4j
public class OpenApiController {
    private final WeatherService weatherService;
    private final KakaoService kakaoService;
    private final ImageUploaderService imageUploaderService;
    private final CityService cityService;

    @GetMapping("/open-api/weather/{base_date}/{base_time}/{nx}/{ny}")
    public ResponseEntity<?> getWeather(@PathVariable String base_date, @PathVariable String base_time,@PathVariable int nx,@PathVariable int ny){
        String result = weatherService.getWeatherForecast(base_date,base_time,nx,ny);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/open-api/region/{longitude}/{latitude}")
    public ResponseEntity<?> getRegion(@PathVariable Double longitude, @PathVariable Double latitude){
        System.out.println("weather");
        String result = kakaoService.getRegion(longitude,latitude).block();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/image")
    public ResponseEntity<?> getUrl(@RequestBody MultipartFile file){
        String result = imageUploaderService.getUrl(file).block();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/open-api/city/{cityName}")
    public ResponseEntity<?> getCityData(@PathVariable String cityName){
        String result = cityService.getCityData(cityName);
        return ResponseEntity.ok().body(result);
    }

}
