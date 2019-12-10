package com.wgs.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class Ch3InterfaceAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ch3InterfaceAuthApplication.class, args);
	}

}
