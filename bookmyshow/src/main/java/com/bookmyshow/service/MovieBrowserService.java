package com.bookmyshow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookmyshow.entity.PaymentAreaForSeats;
import com.bookmyshow.entity.TicketInformation;
import com.bookmyshow.model.AvailableMoviesInformation;

@Service
public interface MovieBrowserService {

	List<AvailableMoviesInformation> findAvailableMoviesByMovieAndTown(String movie, String town) throws Exception;

	TicketInformation bookYourTickets(PaymentAreaForSeats waitingAreaForSeats);

}
