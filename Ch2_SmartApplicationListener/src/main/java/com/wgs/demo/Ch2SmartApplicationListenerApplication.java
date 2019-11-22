package com.wgs.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class Ch2SmartApplicationListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ch2SmartApplicationListenerApplication.class, args);
	}

}
