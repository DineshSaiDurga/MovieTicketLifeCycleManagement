package com.bookmyshow.config;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.commonservice.common.entity.TheaterInformation;
import com.commonservice.common.logic.ServiceLogic;
import com.commonservice.common.modelclasses.EqualsnHashCodeTheaterInfoPojo;
import com.commonservice.common.modelclasses.ServiceRegistry;

@Configuration
public class BookMyShowConfigurationHandler {

	@Autowired
	ServiceLogic serviceLogic;

	@Bean
	public ServiceRegistry serviceRegistry() {

		List<TheaterInformation> theatreinformation = serviceLogic.findAllTheatersInformation();
		HashMap<EqualsnHashCodeTheaterInfoPojo, List<String>> mapofseats = new HashMap<EqualsnHashCodeTheaterInfoPojo, List<String>>();
		theatreinformation.stream().forEach(e -> {
			EqualsnHashCodeTheaterInfoPojo ej = new EqualsnHashCodeTheaterInfoPojo();
			ej.setScreen(e.getScreen());
			ej.setTheaterName(e.getTheaterName());
			ej.setTown(e.getTown());
			mapofseats.put(ej, e.getSeatNumbers());
			ej = null;
		});

		mapofseats.entrySet().stream().forEach(x -> System.out.println(x.getKey() + "..." + x.getValue()));

		return ServiceRegistry.builder().loadingAllAvailableSeatNumbersOfTheater(mapofseats).build();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
