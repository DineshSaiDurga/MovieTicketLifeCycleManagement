package com.commonservice.common.modelclasses;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EqualsnHashCodeTheaterInfoPojo {

	private String theaterName;
	private String screen;
	private String town;
	
}
