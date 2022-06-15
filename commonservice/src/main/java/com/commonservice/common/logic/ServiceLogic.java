package com.commonservice.common.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commonservice.common.entity.TheaterInformation;
import com.commonservice.common.interfaces.ServiceLogicInterface;
import com.commonservice.common.repository.TheaterInformationRepository;

@Service
public class ServiceLogic implements ServiceLogicInterface {

	@Autowired
	TheaterInformationRepository commonRepository;

	@Override
	public List<TheaterInformation> findAllTheatersInformation() {
		return commonRepository.findAll();
	}

}
