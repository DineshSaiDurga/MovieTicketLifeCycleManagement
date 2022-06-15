package com.bookmyshow.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookmyshow.entity.PaymentAreaForSeats;
import com.bookmyshow.entity.TicketInformation;
import com.bookmyshow.model.AvailableMoviesInformation;
import com.bookmyshow.service.MovieBrowserService;
import com.bookmyshow.service.SeatService;
import com.bookmyshow.util.BookMyShowHelper;
import com.commonservice.common.entity.TheatersToMoviesLink;
import com.commonservice.common.modelclasses.ServiceRegistry;

@Service
public class MovieBrowserServiceImpl implements MovieBrowserService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ServiceRegistry serviceRegistry;

	@Autowired
	BookMyShowHelper bookMyShowHelper;

	@Autowired
	SeatService seatService;

	@SuppressWarnings("unchecked")
	@Override
	public List<AvailableMoviesInformation> findAvailableMoviesByMovieAndTown(String movie, String town)
			throws Exception {
		Date date = new Date();

		SimpleDateFormat datecomparsion = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
		datecomparsion.setTimeZone(TimeZone.getTimeZone("IST"));

		SimpleDateFormat timecomparsion = new SimpleDateFormat("HH:mm:ss aa", Locale.US);
		timecomparsion.setTimeZone(TimeZone.getTimeZone("IST"));
		List<AvailableMoviesInformation> listAvailableMoviesInformation = new ArrayList<AvailableMoviesInformation>();

		ResponseEntity<List<TheatersToMoviesLink>> listTheatersToMoviesLink = restTemplate.exchange(
				"http://localhost:8084/api/findAllTheatersToMoviesLink", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<TheatersToMoviesLink>>() {
				});

		List<TheatersToMoviesLink> validListTheatersToMoviesLink = listTheatersToMoviesLink.getBody().stream()
				.filter(x -> serviceRegistry.getLoadingAllAvailableSeatNumbersOfTheater().keySet().stream()
						.anyMatch(y -> y.getScreen().equalsIgnoreCase(x.getScreenName())
								&& y.getTheaterName().equalsIgnoreCase(x.getTheaterName())
								&& y.getTown().equalsIgnoreCase(x.getTown())))
				.collect(Collectors.toList());

		for (TheatersToMoviesLink singleListTheatersToMoviesLink : validListTheatersToMoviesLink) {
			List<String> showtimings = new ArrayList<String>();
			if (singleListTheatersToMoviesLink.getTown().equalsIgnoreCase(town)
					&& singleListTheatersToMoviesLink.getMovieName().equalsIgnoreCase(movie)
					&& date.after(datecomparsion.parse(singleListTheatersToMoviesLink.getEffectiveDate()))
					&& date.before(datecomparsion.parse(singleListTheatersToMoviesLink.getTerminationDate()))) {

				AvailableMoviesInformation availableMoviesInformation = new AvailableMoviesInformation();
				availableMoviesInformation.setMovieName(movie);
				availableMoviesInformation.setTown(town);
				availableMoviesInformation.setTheatreName(singleListTheatersToMoviesLink.getTheaterName());
				availableMoviesInformation.setScreenName(singleListTheatersToMoviesLink.getScreenName());

				for (String eachshow : singleListTheatersToMoviesLink.getShow()) {
					if (timecomparsion.parse(timecomparsion.format(date)).before(timecomparsion.parse(eachshow))) {
						showtimings.add(eachshow);
					}
				}
				availableMoviesInformation.setDate(datecomparsion.parse(datecomparsion.format(date)));
				availableMoviesInformation.setShow(showtimings);
				listAvailableMoviesInformation.add(availableMoviesInformation);
			}
			showtimings = null;
		}

		return listAvailableMoviesInformation;
	}

	@Override
	public TicketInformation bookYourTickets(PaymentAreaForSeats waitingAreaForSeats) {

		PaymentAreaForSeats paymentAreaForSeats = seatService.paymentAreaForSeats(waitingAreaForSeats);

		TicketInformation ticketInformation = seatService.bookPaymentAreaForSeats(paymentAreaForSeats);

		return ticketInformation;

	}

}
