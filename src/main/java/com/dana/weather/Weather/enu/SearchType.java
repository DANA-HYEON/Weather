package com.dana.weather.Weather.enu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum SearchType {

    DAYTIME_HIGHS("낮최고기온"),
    HUMIDITY("습도"),
    MORNING_ROWS("낮최저기온"),
    RAIN_RATE("강수량");

    private String name_KR;


    SearchType(String name_KR) {
        this.name_KR = name_KR;
    }
}
