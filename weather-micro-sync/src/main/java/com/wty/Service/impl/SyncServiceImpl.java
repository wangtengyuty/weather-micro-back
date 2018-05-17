package com.wty.Service.impl;

import com.wty.Service.ISyncService;
import com.wty.client.CityClient;
import com.wty.pojo.Country;
import com.wty.util.JsonUtil;
import com.wty.util.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author wangtengyu
 * @Create 2018-04-23-9:56
 */
@Service("iSyncService")
@Slf4j
public class SyncServiceImpl implements ISyncService {

    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private CityClient cityClient;

    private ResponseEntity<String> responseEntity=null;

    private String bodyString = null;
    private WeatherResponse weatherResponse = null;

    @Override
    public void syncDataByCityKey() throws IOException, JAXBException {
        //// TODO: 2018/5/7 从城市数据api获得城市数据信息
        List<Country> countryList=cityClient.getAllCountry();
        for(Country country:countryList){
            String uri = WEATHER_URI + "citykey=" + country.getCityKey();
            saveWeatherData(uri);
        }
    }

    private void saveWeatherData(String uri) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        responseEntity = restTemplate.getForEntity(uri, String.class);
        if (responseEntity.getStatusCodeValue() == 200) {
            bodyString = responseEntity.getBody();
        }
        valueOperations.set(uri, bodyString, 300L, TimeUnit.SECONDS);
        log.info("weather sync now");
        try {
            weatherResponse = JsonUtil.string2Obj(bodyString, WeatherResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
