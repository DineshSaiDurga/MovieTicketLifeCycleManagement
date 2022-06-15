package com.bookmyshow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bookmyshow.entity.TicketInformation;

public interface BookMyShowRepository extends MongoRepository<TicketInformation, Integer> {

}
