package com.commonservice.common.entity;

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
@Document(collection = "TheaterInformation")
public class TheaterInformation {

	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";
	@Id
	private int id;
	private String theaterName;
	private String screen;
	private List<String> seatNumbers;
	private String town;

}
