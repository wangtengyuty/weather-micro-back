package com.wty.Service.impl;

import com.wty.Service.IWeatherService;
import com.wty.util.JsonUtil;
import com.wty.util.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @Author wangtengyu
 * @Create 2018-04-23-9:56
 */
@Service("iWeatherService")
@Slf4j
public class WeatherServiceImpl implements IWeatherService {

    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";


    @Autowired
    private StringRedisTemplate redisTemplate;

    private String bodyString = null;
    private WeatherResponse weatherResponse = null;

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;
        return getByUri(uri);
    }

    @Override
    public WeatherResponse getDataByCityKey(String cityKey) {
        String uri = WEATHER_URI + "citykey=" + cityKey;
        return getByUri(uri);
    }

    private WeatherResponse getByUri(String uri) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(uri)) {
            bodyString = valueOperations.get(uri);
            log.info("data from redis ");
        }else{
            log.info("no data from redis");
            throw new RuntimeException();
        }
        try {
            weatherResponse = JsonUtil.string2Obj(bodyString, WeatherResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weatherResponse;
    }


}
