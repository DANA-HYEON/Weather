package com.dana.weather.Weather.domain;

import com.dana.weather.Weather.dto.WeatherRequest;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@Entity
public class Weather {

    @Id
    private LocalDate date; //날씨날짜

    private Integer rainRate; //강수확률

    private Integer humidity; //습도

    private Integer morningRows; //아침 최저기온

    private Integer daytimeHighs; //낮 최고기온

    @Builder
    public Weather(LocalDate date, Integer rainRate, Integer humidity, Integer morningRows, Integer daytimeHighs){
        this.date = date;
        this.rainRate = rainRate;
        this.humidity = humidity;
        this.morningRows = morningRows;
        this.daytimeHighs =  daytimeHighs;
    }

    public void update(WeatherRequest.update weather){
        this.rainRate = weather.getRainRate() == null ? rainRate : weather.getRainRate();
        this.humidity = weather.getHumidity() == null ? humidity : weather.getHumidity();
        this.morningRows = weather.getMorningRows() == null ? morningRows : weather.getMorningRows();
        this.daytimeHighs = weather.getDaytimeHighs() == null ? daytimeHighs : weather.getDaytimeHighs();
    }
}
