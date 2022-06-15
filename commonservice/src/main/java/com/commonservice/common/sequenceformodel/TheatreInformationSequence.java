package com.commonservice.common.sequenceformodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "TheatreInformationSequenceSequence")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TheatreInformationSequence {

	@Id
	private String Id;
	private int seq;
}
