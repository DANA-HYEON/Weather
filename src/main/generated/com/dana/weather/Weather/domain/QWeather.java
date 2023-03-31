package com.dana.weather.Weather.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QWeather is a Querydsl query type for Weather
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWeather extends EntityPathBase<Weather> {

    private static final long serialVersionUID = -1089644017L;

    public static final QWeather weather = new QWeather("weather");

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final NumberPath<Integer> daytimeHighs = createNumber("daytimeHighs", Integer.class);

    public final NumberPath<Integer> humidity = createNumber("humidity", Integer.class);

    public final NumberPath<Integer> morningRows = createNumber("morningRows", Integer.class);

    public final NumberPath<Integer> rainRate = createNumber("rainRate", Integer.class);

    public QWeather(String variable) {
        super(Weather.class, forVariable(variable));
    }

    public QWeather(Path<? extends Weather> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWeather(PathMetadata metadata) {
        super(Weather.class, metadata);
    }

}

