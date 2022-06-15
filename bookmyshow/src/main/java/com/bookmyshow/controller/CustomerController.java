package com.bookmyshow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.entity.TicketInformation;
import com.bookmyshow.service.TicketService;


@RestController
@RequestMapping("/api")
public class CustomerController {

	
	@Autowired
	TicketService ticketService;
	
	@GetMapping("/Tickets/{movie}/{town}")
	  public ResponseEntity<List<TicketInformation>> findByTicketBylocatin(@PathVariable("movie") String movie,@PathVariable("town") String town ) {
	   
		List<TicketInformation> tutorialData = ticketService.findTicketByMovieAndTown(movie, town);
	    
	      return new ResponseEntity<List<TicketInformation>>(tutorialData, HttpStatus.OK);
	    
	  }
	
	@PostMapping("/Tickets")
    public TicketInformation saveTicket(@RequestBody TicketInformation Ticket )
    {
        return ticketService.saveTicket(Ticket);
    }
	
}
