package com.predictmatch.liveresults;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LiveResultsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiveResultsServiceApplication.class, args);
	}

}
