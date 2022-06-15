package com.commonservice.common.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.commonservice.common.entity.TheaterInformation;

@Service
public interface ServiceLogicInterface {
	List<TheaterInformation> findAllTheatersInformation();
}
