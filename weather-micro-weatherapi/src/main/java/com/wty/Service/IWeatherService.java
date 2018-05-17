package com.wty.Service;

import com.wty.util.WeatherResponse;

/**
 * @Author wangtengyu
 * @Create 2018-04-23-9:05
 */

public interface IWeatherService {


    WeatherResponse getDataByCityName(String cityName);

    WeatherResponse getDataByCityKey(String cityKey);



}
