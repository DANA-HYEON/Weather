package com.dana.weather.Weather.dto;

import com.dana.weather.Weather.domain.Weather;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
public class WeatherRequest {

    private LocalDate date; //날씨날짜

    @PositiveOrZero(message = "강수확률은 양수만 등록 가능합니다.")
    @Max(value = 100, message = "강수확률은 100%를 초과할 수 없습니다.")
    private Integer rainRate; //강수확률

    @PositiveOrZero(message = "습도는 양수만 등록 가능합니다.")
    @Max(value = 100, message = "습도는 100%를 초과할 수 없습니다.")
    private Integer humidity; //습도

    private Integer morningRows; //아침 최저기온

    private Integer daytimeHighs; //낮 최고기온

    public static Weather toEntity(WeatherRequest dto){
        Weather weather = Weather.builder()
                .date(dto.getDate())
                .rainRate(dto.getRainRate())
                .humidity(dto.getHumidity())
                .morningRows(dto.getMorningRows())
                .daytimeHighs(dto.getDaytimeHighs())
                .build();

        return weather;
    }

    @Getter
    @Setter
    public static class update{

        @PositiveOrZero(message = "강수확률은 양수만 등록 가능합니다.")
        @Max(value = 100, message = "강수확률은 100%를 초과할 수 없습니다.")
        private Integer rainRate; //강수확률

        @PositiveOrZero(message = "습도는 양수만 등록 가능합니다.")
        @Max(value = 100, message = "습도는 100%를 초과할 수 없습니다.")
        private Integer humidity; //습도

        private Integer morningRows; //아침 최저기온

        private Integer daytimeHighs; //낮 최고기온

        public Weather toEntity(LocalDate date){
            Weather weather = Weather.builder()
                    .date(date)
                    .rainRate(rainRate)
                    .humidity(humidity)
                    .morningRows(morningRows)
                    .daytimeHighs(daytimeHighs)
                    .build();

            return weather;
        }
    }
}
