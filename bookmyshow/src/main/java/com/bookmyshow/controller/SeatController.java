package com.bookmyshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.entity.OpenSeatsInformation;
import com.bookmyshow.entity.PaymentAreaForSeats;
import com.bookmyshow.model.SeatInformation;
import com.bookmyshow.service.SeatService;

@RestController
@RequestMapping("/api")
public class SeatController {

	@Autowired
	SeatService seatService;

	@GetMapping("/findOpenSeatsByMovieAndTownAndShowAndDateAndScreenName")
	public ResponseEntity<OpenSeatsInformation> findOpenSeatsByMovieAndTownAndShowAndDateAndScreenName(
			@RequestBody SeatInformation seatInformation) {

		OpenSeatsInformation tutorialData = seatService
				.findOpenSeatsByMovieAndTownAndShowAndDateAndScreenName(seatInformation);

		return new ResponseEntity<OpenSeatsInformation>(tutorialData, HttpStatus.OK);

	}

	@PostMapping("/waitingAreaForSeats")
	public ResponseEntity<PaymentAreaForSeats> waitingAreaForSeats(
			@RequestBody PaymentAreaForSeats paymentAreaForSeats) {
		PaymentAreaForSeats tutorialData = seatService.paymentAreaForSeats(paymentAreaForSeats);

		return new ResponseEntity<PaymentAreaForSeats>(tutorialData, HttpStatus.OK);
	}

}
