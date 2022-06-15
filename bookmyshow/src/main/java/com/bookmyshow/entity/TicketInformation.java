package com.bookmyshow.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "TicketInformation")
public class TicketInformation {

	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";
	@Id
	private int id;
	private Long ticketNumber;
	private String userName;
	private String phoneNumber;
	private String email;
	private String townName;
	private String movieName;
	private String theaterName;
	private List<String> seatNumber;
	private String showtime;
	private Date date;

}
