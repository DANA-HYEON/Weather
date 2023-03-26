package com.dana.weather.Weather.service;

import com.dana.weather.Weather.domain.Weather;
import com.dana.weather.Weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WeatherService {

    @Autowired
    WeatherRepository weatherRepository;

    public Page<Weather> findWeather(String startDate, String endDate, Pageable pageable){
        
        if(startDate == null && endDate == null){
            //전체 값 조회
            return weatherRepository.findAll(pageable);
        }else if(startDate != null && endDate == null){
            //입력한 날짜를 포함하여 이후 날짜의 값 조회
            return weatherRepository.findAllByDateGreaterThanEqual(LocalDate.parse(startDate), pageable);
        }else if(startDate == null && endDate != null){
            //입력한 날짜를 포함하여 이전 날짜의 값 조회
            return weatherRepository.findAllByDateLessThanEqual(LocalDate.parse(endDate), pageable);
        }else{
            //입력한 기간 내의 값 조회
            return weatherRepository.findAllByDateBetween(LocalDate.parse(startDate),LocalDate.parse(endDate), pageable);
        }
    }

    public void addWeather(Weather weather){
        weatherRepository.save(weather);
    }
}
