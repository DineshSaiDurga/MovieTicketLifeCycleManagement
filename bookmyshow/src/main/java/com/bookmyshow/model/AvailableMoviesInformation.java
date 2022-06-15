package com.bookmyshow.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AvailableMoviesInformation {

	private String theatreName;
	private String movieName;
	private String screenName;
	private String town;
	private List<String> show;
	private Date date;
}
