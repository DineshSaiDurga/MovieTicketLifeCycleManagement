package com.bookyourtheatre.theatre.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.commonservice.common.entity.TheaterInformation;
import com.commonservice.common.entity.TheatersToMoviesLink;

@Service
public interface TheatersToMoviesLinkService {

	List<TheatersToMoviesLink> findAllTheatersToMoviesLink();

	TheatersToMoviesLink insertANewMovieWithTheatersAndScreensAndTown(TheatersToMoviesLink TheatersToMoviesLink);

	TheatersToMoviesLink updateMovieToExistingTheatersAndScreensAndTown(TheatersToMoviesLink TheatersToMoviesLink);

	void deleteMovieToExistingTheatersAndScreensAndTown(TheatersToMoviesLink TheatersToMoviesLink);

	TheaterInformation insertBasicTheaterInformation(TheaterInformation theaterInformation);
}
