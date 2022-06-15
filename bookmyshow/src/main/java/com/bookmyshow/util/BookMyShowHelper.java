package com.bookmyshow.util;

import static com.commonservice.common.entity.TheatersToMoviesLink.SEQUENCE_NAME;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookmyshow.entity.OpenSeatsInformation;
import com.bookmyshow.entity.PaymentAreaForSeats;
import com.bookmyshow.model.SeatInformation;
import com.commonservice.common.logic.SequenceGeneratorService;
import com.commonservice.common.sequenceformodel.OpenSeatsInformationSequence;
import com.commonservice.common.sequenceformodel.PaymentAreaForSeatsSequence;

@Component
public class BookMyShowHelper {

	@Autowired
	SequenceGeneratorService sequenceGeneratorService;

	@Autowired
	PaymentAreaForSeatsSequence paymentAreaForSeatsSequence;

	@Autowired
	OpenSeatsInformationSequence openSeatsInformationSequence;

	public PaymentAreaForSeats builderForPaymentAreaForSeatsUsingSeatInformation(SeatInformation seatInformation) {
		Date date = new Date();
		TimeZone.getDefault();
		PaymentAreaForSeatsSequence paymentAreaForSeatsSequence1 = (PaymentAreaForSeatsSequence) sequenceGeneratorService
				.getSequenceNumber(SEQUENCE_NAME, paymentAreaForSeatsSequence);

		int id = !Objects.isNull(paymentAreaForSeatsSequence1) ? paymentAreaForSeatsSequence1.getSeq() : 1;

		PaymentAreaForSeats waitingAreaForSeats = PaymentAreaForSeats.builder().id(id)
				.userName(seatInformation.getUserName()).phoneNumber(seatInformation.getPhoneNumber())
				.email(seatInformation.getEmail()).movieName(seatInformation.getMovieName())
				.screenName(seatInformation.getScreenName()).show(seatInformation.getShow())
				.theaterName(seatInformation.getTheaterName()).town(seatInformation.getTown()).date(date).build();
		return waitingAreaForSeats;

	}

	public SeatInformation builderForSeatInformationUsingPaymentAreaForSeats(PaymentAreaForSeats waitingAreaForSeats) {
		Date date = new Date();
		TimeZone.getDefault();

		SeatInformation seatInformation = SeatInformation.builder().userName(waitingAreaForSeats.getUserName())
				.phoneNumber(waitingAreaForSeats.getPhoneNumber()).email(waitingAreaForSeats.getEmail())
				.movieName(waitingAreaForSeats.getMovieName()).screenName(waitingAreaForSeats.getScreenName())
				.show(waitingAreaForSeats.getShow()).theaterName(waitingAreaForSeats.getTheaterName())
				.town(waitingAreaForSeats.getTown()).date(date).build();

		return seatInformation;

	}

	public OpenSeatsInformation builderForOpenSeatsInformationUsingPaymentAreaForSeats(SeatInformation seatInformation,
			List<String> seatNumbers) {
		Date date = new Date();
		TimeZone.getDefault();

		OpenSeatsInformationSequence openSeatsInformationSequence1 = (OpenSeatsInformationSequence) sequenceGeneratorService
				.getSequenceNumber(SEQUENCE_NAME, openSeatsInformationSequence);

		int id = !Objects.isNull(openSeatsInformationSequence1) ? openSeatsInformationSequence1.getSeq() : 1;

		return OpenSeatsInformation.builder().id(id).movieName(seatInformation.getMovieName())
				.screenName(seatInformation.getScreenName()).show(seatInformation.getShow())
				.theaterName(seatInformation.getTheaterName()).town(seatInformation.getTown())
				.openSeatsInformation(seatNumbers).status("OPEN").date(date).build();

	}

}
