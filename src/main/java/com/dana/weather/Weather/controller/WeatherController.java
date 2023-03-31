package com.dana.weather.Weather.controller;

import com.dana.weather.Weather.domain.Weather;
import com.dana.weather.Weather.dto.Response;
import com.dana.weather.Weather.dto.WeatherRequest;
import com.dana.weather.Weather.enu.SearchType;
import com.dana.weather.Weather.repository.WeatherRepository;
import com.dana.weather.Weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

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
                                      @RequestParam(required = false) SearchType searchTypes,
                                      @RequestParam(required = false) Integer startNumber,
                                      @RequestParam(required = false) Integer endNumber,
                                      @PageableDefault(size = 5, sort = "date", direction = Sort.Direction.DESC) Pageable pageable){
        try{
            Page<Weather> weathers = weatherService.findWeather(startDate,endDate,pageable, searchTypes, startNumber, endNumber);
            return ResponseEntity.ok().body(weathers);
        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Response(400, e.getMessage()));
        }
    }

    @PutMapping("/{date}")
    public ResponseEntity weatherModify(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                        @RequestBody(required = false) @Valid WeatherRequest.update weather){
        try{
            weatherService.modifyWeather(date, weather);
            return ResponseEntity.ok().body(new Response(200, "success"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Response(400, e.getMessage()));
        }
    }

    @PostMapping()
    public ResponseEntity weatherAdd(@RequestBody @Valid WeatherRequest weather){
        try{
            weatherService.saveWeather(weather);
            return ResponseEntity.ok().body(new Response(200, "success"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Response(400, e.getMessage()));
        }
    }

    @DeleteMapping()
    public ResponseEntity weatherRemove(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")List<LocalDate> date){
        try{
            weatherService.removeWeather(date);
            return ResponseEntity.ok().body(new Response(200, "success"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Response(400, e.getMessage()));
        }
    }
}
