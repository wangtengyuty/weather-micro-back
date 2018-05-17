package com.wty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class WeatherZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherZuulApplication.class, args);
	}
}
