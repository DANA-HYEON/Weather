package com.dana.weather.Weather.repository;

import com.dana.weather.Weather.domain.Weather;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Page<Weather> findAllByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

    Page<Weather> findAllByDateLessThanEqual(LocalDate endDate, Pageable pageable);

    Page<Weather> findAllByDateGreaterThanEqual(LocalDate startDate, Pageable pageable);
}
