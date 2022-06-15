package com.commonservice.common.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.commonservice.common.entity.TheaterInformation;

@Repository
public interface TheaterInformationRepository extends MongoRepository<TheaterInformation, Integer> {

}
