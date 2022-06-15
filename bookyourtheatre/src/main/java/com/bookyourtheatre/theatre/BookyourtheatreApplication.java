package com.bookyourtheatre.theatre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.commonservice.common.*", "com.bookyourtheatre.*" })
@EnableMongoRepositories(basePackages = { "com.commonservice.common.*", "com.bookyourtheatre.*" })
public class BookyourtheatreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookyourtheatreApplication.class, args);
	}

}
