package com.wty.controller;

import com.wty.Service.ICountryDataService;
import com.wty.pojo.Country;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author wangtengyu
 * @Create 2018-04-23-9:54
 */
@RestController
@CrossOrigin
@Slf4j
public class CountryController {

    @Autowired
    private ICountryDataService iCountryDataService;


    @GetMapping("/getAllCountry")
    public List<Country> getAllCountry(){
        log.info("调用城市数据服务获取城市数据");
        try {
            return iCountryDataService.getCountryList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
