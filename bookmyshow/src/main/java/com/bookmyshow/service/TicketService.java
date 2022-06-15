package com.bookmyshow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookmyshow.entity.TicketInformation;


@Service
public interface TicketService {

	List<TicketInformation>  findTicketByMovieAndTown(String movie,String town);
	TicketInformation saveTicket(TicketInformation ticket);
	
}
