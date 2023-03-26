package com.dana.weather.Weather.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;


@Getter
@Entity
public class Weather {
    @Id
    private LocalDate date; //날씨날짜

    @PositiveOrZero(message = "강수확률은 양수만 등록 가능합니다.")
    @Max(value = 100, message = "강수확률은 100%를 초과할 수 없습니다.")
    private Integer rainRate; //강수확률

    @PositiveOrZero(message = "습도는 양수만 등록 가능합니다.")
    @Max(value = 100, message = "습도는 100%를 초과할 수 없습니다.")
    private Integer humidity; //습도

    private Integer morningRows; //아침 최저기온

    private Integer daytimeHighs; //낮 최고기온 
}
