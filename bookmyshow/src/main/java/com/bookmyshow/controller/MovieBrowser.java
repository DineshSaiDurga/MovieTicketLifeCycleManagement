package com.bookmyshow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.entity.PaymentAreaForSeats;
import com.bookmyshow.entity.TicketInformation;
import com.bookmyshow.model.AvailableMoviesInformation;
import com.bookmyshow.service.MovieBrowserService;

@RestController
@RequestMapping("/api")
public class MovieBrowser {

	@Autowired
	MovieBrowserService movieBrowserService;

	@GetMapping("/findAvailableMovies/{movie}/{town}")
	public ResponseEntity<List<AvailableMoviesInformation>> findAvailableMoviesByMovieAndTown(
			@PathVariable("movie") String movie, @PathVariable("town") String town) {

		List<AvailableMoviesInformation> movieBrowserInfo = null;

		System.out.println("entered");

		try {
			movieBrowserInfo = movieBrowserService.findAvailableMoviesByMovieAndTown(movie, town);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<List<AvailableMoviesInformation>>(movieBrowserInfo, HttpStatus.OK);

	}

	@PostMapping("/bookYourTickets")
	public ResponseEntity<TicketInformation> bookYourTickets(@RequestBody PaymentAreaForSeats waitingAreaForSeats) {
		TicketInformation tutorialData = movieBrowserService.bookYourTickets(waitingAreaForSeats);

		return new ResponseEntity<TicketInformation>(tutorialData, HttpStatus.OK);
	}

}
