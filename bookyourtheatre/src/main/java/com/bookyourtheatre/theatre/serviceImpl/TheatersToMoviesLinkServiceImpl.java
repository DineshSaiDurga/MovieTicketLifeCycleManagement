package com.bookyourtheatre.theatre.serviceImpl;

import static com.commonservice.common.entity.TheatersToMoviesLink.SEQUENCE_NAME;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.bookyourtheatre.theatre.repository.TheatersToMoviesLinkRepository;
import com.bookyourtheatre.theatre.service.TheatersToMoviesLinkService;
import com.commonservice.common.entity.TheaterInformation;
import com.commonservice.common.entity.TheatersToMoviesLink;
import com.commonservice.common.logic.SequenceGeneratorService;
import com.commonservice.common.repository.TheaterInformationRepository;
import com.commonservice.common.sequenceformodel.TheaterToMoviesLinkSequence;
import com.commonservice.common.sequenceformodel.TheatreInformationSequence;

@Service
public class TheatersToMoviesLinkServiceImpl implements TheatersToMoviesLinkService {

	@Autowired
	TheatersToMoviesLinkRepository theatersToMoviesLinkRepository;

	@Autowired
	TheaterInformationRepository theaterInformationRepository;

	@Autowired
	SequenceGeneratorService sequenceGeneratorService;

	@Autowired
	TheaterToMoviesLinkSequence theaterToMoviesLinkSequence;

	@Autowired
	TheatreInformationSequence theaterInformationSequence;

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public List<TheatersToMoviesLink> findAllTheatersToMoviesLink() {
		// TODO Auto-generated method stub
		// theatersToMoviesLinkRepository.findAll();
		return theatersToMoviesLinkRepository.findAll();
	}

	@Override
	public TheatersToMoviesLink insertANewMovieWithTheatersAndScreensAndTown(
			TheatersToMoviesLink theatersToMoviesLink) {
		// TODO Auto-generated method stub
		TheaterToMoviesLinkSequence theaterToMoviesLinkSequence1 = (TheaterToMoviesLinkSequence) sequenceGeneratorService
				.getSequenceNumber(SEQUENCE_NAME, theaterToMoviesLinkSequence);
		theatersToMoviesLink
				.setId(!Objects.isNull(theaterToMoviesLinkSequence1) ? theaterToMoviesLinkSequence1.getSeq() : 1);

		return theatersToMoviesLinkRepository.save(theatersToMoviesLink);
	}

	@Override
	public TheatersToMoviesLink updateMovieToExistingTheatersAndScreensAndTown(
			TheatersToMoviesLink TheatersToMoviesLink) {
		// TODO Auto-generated method stub

		Query query = new Query(
				new Criteria().andOperator(Criteria.where("movieName").is(TheatersToMoviesLink.getMovieName()),
						Criteria.where("screenName").is(TheatersToMoviesLink.getScreenName()),
						Criteria.where("town").is(TheatersToMoviesLink.getTown())));

		List<TheatersToMoviesLink> theatersToMoviesLink = mongoOperations.find(query, TheatersToMoviesLink.class);

		TheatersToMoviesLink.setId(theatersToMoviesLink.get(0).getId());
		return theatersToMoviesLinkRepository.save(TheatersToMoviesLink);
	}

	@Override
	public void deleteMovieToExistingTheatersAndScreensAndTown(TheatersToMoviesLink TheatersToMoviesLink) {
		// TODO Auto-generated method stub
		theatersToMoviesLinkRepository.delete(TheatersToMoviesLink);

	}

	@Override
	public TheaterInformation insertBasicTheaterInformation(TheaterInformation theaterInformation) {
		// TODO Auto-generated method stub

		TheatreInformationSequence theaterToMoviesLinkSequence1 = (TheatreInformationSequence) sequenceGeneratorService
				.getSequenceNumber(SEQUENCE_NAME, theaterInformationSequence);

		theaterInformation
				.setId(!Objects.isNull(theaterToMoviesLinkSequence1) ? theaterToMoviesLinkSequence1.getSeq() : 1);

		return theaterInformationRepository.save(theaterInformation);
	}

}
