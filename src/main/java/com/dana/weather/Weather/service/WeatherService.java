package com.dana.weather.Weather.service;

import com.dana.weather.Weather.domain.QWeather;
import com.dana.weather.Weather.domain.Weather;
import com.dana.weather.Weather.dto.WeatherRequest;
import com.dana.weather.Weather.enu.SearchType;
import com.dana.weather.Weather.repository.WeatherRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    JPAQueryFactory queryFactory;

    public Page<Weather> findWeather(String startDate, String endDate, Pageable pageable, SearchType searchTypes, Integer startNumber, Integer endNumber){

        List<Weather> list = queryFactory.selectFrom(QWeather.weather)
                .where(eqDate(startDate, endDate), eqSearchType(searchTypes, startNumber, endNumber))
                .fetch();

        Integer count = queryFactory.select(QWeather.weather.date)
                .from(QWeather.weather)
                .where(eqDate(startDate, endDate))
                .fetch()
                .size();

        return new PageImpl<>(list, pageable, count);
    }


    public BooleanExpression eqDate(String startDate, String endDate){

        if(startDate == null && endDate == null){
            return null;
        }else if(startDate != null && endDate == null){//            입력한 날짜를 포함하여 이후 날짜의 값 조회
            return QWeather.weather.date.after(LocalDate.parse(startDate));
        }else if(startDate == null && endDate != null){//            입력한 날짜를 포함하여 이전 날짜의 값 조회
            return QWeather.weather.date.before(LocalDate.parse(endDate));
        }else{//            입력한 기간 내의 값 조회
            return QWeather.weather.date.between(LocalDate.parse(startDate), LocalDate.parse(endDate));
        }
    }


    public BooleanExpression eqSearchType(SearchType searchTypes, Integer startNumber, Integer endNumber){
        if (startNumber == null && endNumber == null) {
            return null;
        }

        if (searchTypes.name().equals("DAYTIME_HIGHS")) {
            return ParameterNullCheck(QWeather.weather.daytimeHighs,startNumber,endNumber);
        } else if (searchTypes.name().equals("HUMIDITY")) {
            return ParameterNullCheck(QWeather.weather.humidity,startNumber,endNumber);
        } else if (searchTypes.name().equals("MORNING_ROWS")) {
            return ParameterNullCheck(QWeather.weather.morningRows,startNumber,endNumber);
        } else if (searchTypes.name().equals("RAIN_RATE")) {
            return ParameterNullCheck(QWeather.weather.rainRate,startNumber,endNumber);
        } else {
            return null;
        }
    }

    public BooleanExpression ParameterNullCheck(NumberPath path, Integer startNumber, Integer endNumber){
            if(startNumber != null && endNumber == null){
                return path.goe(startNumber);
            } else if (startNumber == null && endNumber != null){
                return path.loe(endNumber);
            }else{
                return path.between(startNumber,endNumber);
            }
    }

    @Transactional
    public void modifyWeather(LocalDate date, WeatherRequest.update dto) {
         Weather result = weatherRepository.findFirstByDate(date).orElseThrow(() -> new IllegalArgumentException("날짜에 해당하는 정보가 없습니다."));

         result.update(dto);
    }

    public void saveWeather(WeatherRequest dto){
        Optional<Weather> result = weatherRepository.findFirstByDate(dto.getDate());

        if (result.isPresent()){
            throw new IllegalArgumentException("해당 날짜의 날씨정보가 저장되어 있습니다.");
        }

        Weather weather = dto.toEntity(dto);

        weatherRepository.save(weather);
    }

    @Transactional
    public void removeWeather(List<LocalDate> date){

        weatherRepository.deleteAllByDateIn(date);
    }

}
