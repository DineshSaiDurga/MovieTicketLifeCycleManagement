package com.bookyourtheatre.theatre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookyourtheatre.theatre.service.TheatersToMoviesLinkService;
import com.commonservice.common.entity.TheaterInformation;
import com.commonservice.common.entity.TheatersToMoviesLink;

@RestController
@RequestMapping("/api")
public class TheaterAgentController {

	@Autowired
	TheatersToMoviesLinkService theaterstomovieslinkservice;

	@GetMapping("/findAllTheatersToMoviesLink")
	public ResponseEntity<List<TheatersToMoviesLink>> findAllMovies() {

		List<TheatersToMoviesLink> theaterstomovieslink = theaterstomovieslinkservice.findAllTheatersToMoviesLink();

		return new ResponseEntity<List<TheatersToMoviesLink>>(theaterstomovieslink, HttpStatus.OK);

	}

	@PostMapping("/insertANewMovieWithTheatersAndScreensAndTown")
	public ResponseEntity<TheatersToMoviesLink> insertANewMovieToExistingTheaters(
			@RequestBody TheatersToMoviesLink theatersToMoviesLink) {

		TheatersToMoviesLink theaterstomovieslink = theaterstomovieslinkservice
				.insertANewMovieWithTheatersAndScreensAndTown(theatersToMoviesLink);

		return new ResponseEntity<TheatersToMoviesLink>(theaterstomovieslink, HttpStatus.OK);

	}

	@PutMapping("/updateMovieToExistingTheatersAndScreensAndTown")
	public ResponseEntity<TheatersToMoviesLink> updateMovieToExistingTheatersAndScreens(
			@RequestBody TheatersToMoviesLink theatersToMoviesLink) {

		TheatersToMoviesLink theaterstomovieslink = theaterstomovieslinkservice
				.updateMovieToExistingTheatersAndScreensAndTown(theatersToMoviesLink);

		return new ResponseEntity<TheatersToMoviesLink>(theaterstomovieslink, HttpStatus.OK);
	}

	@DeleteMapping("/deleteMovieToExistingTheatersAndScreensAndTown")
	public ResponseEntity<String> deleteMovieToExistingTheatersAndScreens(
			@RequestBody TheatersToMoviesLink theatersToMoviesLink) {

		theaterstomovieslinkservice.deleteMovieToExistingTheatersAndScreensAndTown(theatersToMoviesLink);

		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
	}

	// Main Theater Information to be provided by Theater Agent but for this
	// services need to be bounced - bookmyshow
	@PostMapping("/insertBasicTheaterInformation")
	public ResponseEntity<TheaterInformation> insertBasicTheaterInformation(
			@RequestBody TheaterInformation theaterInformation) {

		TheaterInformation theaterInformation1 = theaterstomovieslinkservice
				.insertBasicTheaterInformation(theaterInformation);

		return new ResponseEntity<TheaterInformation>(theaterInformation1, HttpStatus.OK);

	}

}
