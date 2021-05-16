package com.converter;

import org.springframework.stereotype.Component;

import com.dto.InteresDto;
import com.entity.InteresEntity;

@Component
public class InteresConverter {
	public InteresDto toDto(InteresEntity ie) {
		InteresDto i = new InteresDto();
		i.setId(ie.getId());
		i.setNumber(ie.getNumber());
		i.setRatio(ie.getRatio());
		i.setType(ie.getType().toString());
		return i;
	}
}
