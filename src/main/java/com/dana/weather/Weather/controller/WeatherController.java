package com.dana.weather.Weather.controller;

import com.dana.weather.User.dto.Response;
import com.dana.weather.Weather.domain.Weather;
import com.dana.weather.Weather.repository.WeatherRepository;
import com.dana.weather.Weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    WeatherService weatherService;

    @GetMapping()
    public ResponseEntity weatherList(@RequestParam(required = false) String startDate,
                                      @RequestParam(required = false) String endDate,
                                      @PageableDefault(size=2) Pageable pageable){
        try{
            Page<Weather> weathers = weatherService.findWeather(startDate,endDate,pageable);
            return ResponseEntity.ok().body(weathers);
        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Response(400, e.getMessage()));
        }
    }

    @PostMapping()
    public ResponseEntity weatherAdd(@RequestBody Weather weather){
        try{
            weatherService.addWeather(weather);
            return ResponseEntity.ok().body(new Response(200, "success"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Response(400, e.getMessage()));
        }
    }
}
