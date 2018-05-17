package com.wty.controller;

import com.wty.Service.IWeatherService;
import com.wty.util.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wangtengyu
 * @Create 2018-04-23-9:54
 */
@RestController
@CrossOrigin
@Slf4j
public class WeatherController {

    @Autowired
    private IWeatherService IWeatherService;

    @GetMapping("/cityName/{cityName}")
    public WeatherResponse getDataByCityName(@PathVariable("cityName") String cityName){
        return IWeatherService.getDataByCityName(cityName);
    }

    @GetMapping("/cityKey/{cityKey}")
    public WeatherResponse getDataByCityId(@PathVariable("cityKey") String cityKey){
        log.info("调用天气预报服务");
        return IWeatherService.getDataByCityKey(cityKey);
    }

}
