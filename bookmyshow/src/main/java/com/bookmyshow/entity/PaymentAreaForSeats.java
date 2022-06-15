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
@Document(collection = "PaymentAreaForSeats")
public class PaymentAreaForSeats {

	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";

	@Id
	private int id;
	private String userName;
	private String phoneNumber;
	private String email;
	private String theaterName;
	private String movieName;
	private String screenName;
	private String town;
	private String show;
	private List<String> waitingSeatsInformation;
	private String status;
	private Date date;

}
