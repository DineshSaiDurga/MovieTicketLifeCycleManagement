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
@Document(collection = "OpenSeatsInformation")
public class OpenSeatsInformation {

	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";
	@Id
	private int id;
	private String theaterName;
	private String movieName;
	private String screenName;
	private String town;
	private String show;
	private List<String> openSeatsInformation;
	private String status;
	private Date date;

}
