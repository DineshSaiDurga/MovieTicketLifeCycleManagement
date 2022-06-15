package com.bookmyshow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.bookmyshow.entity.PaymentAreaForSeats;

public interface PaymentAreaForSeatsRepository extends MongoRepository<PaymentAreaForSeats, Integer> {

	@Query("{'movieName':?0, 'screenName' :?1 , 'show' : ?2 , 'theaterName' : ?3 , 'town' : ?4}")
	PaymentAreaForSeats findWaitingSeatsByMovieAndTownAndShowAndDateAndScreenName(String movieName, String screenName,
			String show, String theaterName, String town);
}
