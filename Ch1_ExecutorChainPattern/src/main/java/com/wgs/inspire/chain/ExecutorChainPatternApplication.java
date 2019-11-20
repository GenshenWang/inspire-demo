package com.wgs.inspire.chain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ExecutorChainPatternApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExecutorChainPatternApplication.class, args);
	}

}
