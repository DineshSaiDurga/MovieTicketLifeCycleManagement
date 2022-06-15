package com.bookyourtheatre.theatre.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.commonservice.common.entity.TheatersToMoviesLink;

@Repository
public interface TheatersToMoviesLinkRepository extends MongoRepository<TheatersToMoviesLink, Integer> {

}
