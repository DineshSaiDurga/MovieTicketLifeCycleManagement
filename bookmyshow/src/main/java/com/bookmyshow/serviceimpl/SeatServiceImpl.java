package com.bookmyshow.serviceimpl;

import static com.commonservice.common.entity.TheatersToMoviesLink.SEQUENCE_NAME;

import java.time.Clock;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookmyshow.entity.OpenSeatsInformation;
import com.bookmyshow.entity.PaymentAreaForSeats;
import com.bookmyshow.entity.TicketInformation;
import com.bookmyshow.model.SeatInformation;
import com.bookmyshow.repository.BookMyShowRepository;
import com.bookmyshow.repository.OpenSeatsInformationRepository;
import com.bookmyshow.repository.PaymentAreaForSeatsRepository;
import com.bookmyshow.service.SeatService;
import com.bookmyshow.util.BookMyShowHelper;
import com.commonservice.common.logic.SequenceGeneratorService;
import com.commonservice.common.modelclasses.ServiceRegistry;
import com.commonservice.common.sequenceformodel.PaymentAreaForSeatsSequence;
import com.commonservice.common.sequenceformodel.TicketInformationSequence;

@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	OpenSeatsInformationRepository openSeatsInformationRepository;

	@Autowired
	PaymentAreaForSeatsRepository waitingAreaForSeatsRepository;

	@Autowired
	ServiceRegistry serviceRegistry;

	@Autowired
	BookMyShowRepository bookMyShowRepository;

	@Autowired
	BookMyShowHelper bookMyShowHelper;

	@Autowired
	SequenceGeneratorService sequenceGeneratorService;

	@Autowired
	TicketInformationSequence ticketInformationSequence;

	@Autowired
	PaymentAreaForSeatsSequence paymentAreaForSeatsSequence;

	@Override
	public OpenSeatsInformation findOpenSeatsByMovieAndTownAndShowAndDateAndScreenName(
			SeatInformation seatInformation) {
		// TODO Auto-generated method stub

		OpenSeatsInformation openSeatsInformation1 = openSeatsInformationRepository
				.findOpenSeatsByMovieAndTownAndShowAndDateAndScreenName(seatInformation.getMovieName(),
						seatInformation.getScreenName(), seatInformation.getShow(), seatInformation.getTheaterName(),
						seatInformation.getTown());
		// First Person Entered

		if (openSeatsInformation1 == null) {
			List<String> seatNumbers = serviceRegistry.getLoadingAllAvailableSeatNumbersOfTheater().entrySet().stream()
					.filter(e -> e.getKey().getTheaterName().equalsIgnoreCase(seatInformation.getTheaterName())
							&& e.getKey().getScreen().equalsIgnoreCase(seatInformation.getScreenName())
							&& e.getKey().getTown().equalsIgnoreCase(seatInformation.getTown()))
					.findAny().get().getValue();

			openSeatsInformation1 = bookMyShowHelper
					.builderForOpenSeatsInformationUsingPaymentAreaForSeats(seatInformation, seatNumbers);

		}
		return openSeatsInformation1;
	}

	@Override
	@Transactional(readOnly = false)
	public PaymentAreaForSeats paymentAreaForSeats(PaymentAreaForSeats waitingAreaForSeats) {
		// TODO Auto-generated method stub

		// Step1:Find open seats
		OpenSeatsInformation openSeatsInformation = findOpenSeatsByMovieAndTownAndShowAndDateAndScreenName(
				bookMyShowHelper.builderForSeatInformationUsingPaymentAreaForSeats(waitingAreaForSeats));
		// Step 2 :move open seats to waiting

		List<String> openSeatsInformationSeatInfo = openSeatsInformation.getOpenSeatsInformation();

		if (openSeatsInformationSeatInfo != null) {
			if (waitingAreaForSeats.getWaitingSeatsInformation() != null) {
				openSeatsInformationSeatInfo.removeAll(waitingAreaForSeats.getWaitingSeatsInformation());
			} else {
				// first user
			}
		} else {
			// All tickets are booked
		}

		// Step 3 : update Open seats to waiting
		openSeatsInformation.setOpenSeatsInformation(openSeatsInformationSeatInfo);

		openSeatsInformationRepository.save(openSeatsInformation);

		PaymentAreaForSeatsSequence paymentAreaForSeatsSequence1 = (PaymentAreaForSeatsSequence) sequenceGeneratorService
				.getSequenceNumber(SEQUENCE_NAME, paymentAreaForSeatsSequence);
		int id = !Objects.isNull(paymentAreaForSeatsSequence1) ? paymentAreaForSeatsSequence1.getSeq() : 1;
		waitingAreaForSeats.setId(id);

		waitingAreaForSeatsRepository.save(waitingAreaForSeats);

		return waitingAreaForSeats;
	}

	@Override
	@Transactional(readOnly = false)
	public void cancellingOrClosingOrTimeOutPaymentAreaForSeats(PaymentAreaForSeats waitingAreaForSeats) {

		waitingAreaForSeatsRepository.delete(waitingAreaForSeats);

		OpenSeatsInformation openSeatsInformation = findOpenSeatsByMovieAndTownAndShowAndDateAndScreenName(
				bookMyShowHelper.builderForSeatInformationUsingPaymentAreaForSeats(waitingAreaForSeats));

		openSeatsInformation.getOpenSeatsInformation().addAll(waitingAreaForSeats.getWaitingSeatsInformation());

	}

	@Override
	@Transactional(readOnly = false)
	public TicketInformation bookPaymentAreaForSeats(PaymentAreaForSeats waitingAreaForSeats) {

		waitingAreaForSeatsRepository.delete(waitingAreaForSeats);

		Date date = new Date();
		TimeZone.getDefault();

		TicketInformationSequence ticketInformationSequence1 = (TicketInformationSequence) sequenceGeneratorService
				.getSequenceNumber(SEQUENCE_NAME, ticketInformationSequence);

		TicketInformation ticketInformation = TicketInformation.builder()
				.id(!Objects.isNull(ticketInformationSequence1) ? ticketInformationSequence1.getSeq() : 1)
				.ticketNumber(Clock.systemDefaultZone().millis()).userName(waitingAreaForSeats.getUserName())
				.phoneNumber(waitingAreaForSeats.getPhoneNumber()).email(waitingAreaForSeats.getEmail())
				.townName(waitingAreaForSeats.getTown()).movieName(waitingAreaForSeats.getMovieName())
				.theaterName(waitingAreaForSeats.getTheaterName())
				.seatNumber(waitingAreaForSeats.getWaitingSeatsInformation()).showtime(waitingAreaForSeats.getShow())
				.date(date).build();

		return bookMyShowRepository.save(ticketInformation);

	}

}
