package com.bookmyshow.service;

import org.springframework.stereotype.Service;

import com.bookmyshow.entity.OpenSeatsInformation;
import com.bookmyshow.entity.PaymentAreaForSeats;
import com.bookmyshow.entity.TicketInformation;
import com.bookmyshow.model.SeatInformation;

@Service
public interface SeatService {

	OpenSeatsInformation findOpenSeatsByMovieAndTownAndShowAndDateAndScreenName(SeatInformation seatInformation);

	void cancellingOrClosingOrTimeOutPaymentAreaForSeats(PaymentAreaForSeats waitingAreaForSeats);

	TicketInformation bookPaymentAreaForSeats(PaymentAreaForSeats waitingAreaForSeats);

	PaymentAreaForSeats paymentAreaForSeats(PaymentAreaForSeats waitingAreaForSeats);

}
