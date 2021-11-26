package com.predictmatch.liveresults;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.predictmatch.liveresults.service.LiveResultsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.nio.file.Paths;

@SpringBootApplication
@EnableEurekaClient
public class LiveResultsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiveResultsServiceApplication.class, args);
	}

}
