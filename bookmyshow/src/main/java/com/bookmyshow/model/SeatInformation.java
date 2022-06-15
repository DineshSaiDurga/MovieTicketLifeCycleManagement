package com.bookmyshow.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatInformation {

	private String userName;
	private String phoneNumber;
	private String email;
	private String theaterName;
	private String movieName;
	private String screenName;
	private String town;
	private String show;
	private String status;
	private Date date;

}
