package com.wty.client;

import com.wty.pojo.Country;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author wangtengyu
 * @Create 2018-05-07-14:48
 */
@FeignClient(value = "weather-micro-citydataapi")
public interface CityClient {

    @GetMapping("/getAllCountry")
    List<Country> getAllCountry();
}
