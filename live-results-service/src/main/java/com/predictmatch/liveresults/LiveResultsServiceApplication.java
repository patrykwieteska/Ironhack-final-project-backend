package com.predictmatch.liveresults;

import com.predictmatch.liveresults.config.ApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LiveResultsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiveResultsServiceApplication.class, args);
	}

}
