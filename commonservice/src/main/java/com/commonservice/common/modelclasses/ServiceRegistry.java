package com.commonservice.common.modelclasses;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRegistry {

	private Map<EqualsnHashCodeTheaterInfoPojo,List<String>> loadingAllAvailableSeatNumbersOfTheater;
	
}
