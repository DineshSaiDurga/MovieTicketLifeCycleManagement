package com.commonservice.common.logic;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {

	@Autowired
	private MongoOperations mongoOperations;

	public Object getSequenceNumber(String sequenceName, Object abstractSequence) {
		Query query = new Query(Criteria.where("id").is(sequenceName));
		Update update = new Update().inc("seq", 1);
		/*
		 * AbstractSequence counter = (AbstractSequence)
		 * mongoOperations.findAndModify(query, update,
		 * options().returnNew(true).upsert(true), abstractSequence.getClass());
		 */
		return mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true),
				abstractSequence.getClass());
		// return !Objects.isNull(counter) ? counter.getSeq() : 1;
		// return 7;
	}

}
