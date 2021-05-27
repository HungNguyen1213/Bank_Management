package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.controller.BookSavingController;
import com.dto.InteresDto;
import com.entity.InteresEntity.Type;

public class TestFilterByType {
	
	private BookSavingController controller = new BookSavingController();
	
	@Test
	public void test1() {
		List<InteresDto> listDto = new ArrayList<>();
		List<InteresDto> rs = controller.filterByType(listDto, Type.NOLIMIT);
		assertEquals(true, rs.isEmpty());
	}
	
	@Test
	public void test2() {
		List<InteresDto> listDto = new ArrayList<>();
		InteresDto i = new InteresDto();
		i.setId((long)1);
		i.setNumber(0);
		i.setRatio((float)0.3);
		i.setType(Type.NOLIMIT.toString());
		listDto.add(i);
		List<InteresDto> rs = controller.filterByType(listDto, Type.NOLIMIT);
		assertEquals(1, rs.size());
	}
	
	@Test
	public void test3() {
		List<InteresDto> listDto = new ArrayList<>();
		InteresDto i = new InteresDto();
		i.setId((long)1);
		i.setNumber(0);
		i.setRatio((float)0.3);
		i.setType(Type.NOLIMIT.toString());
		listDto.add(i);
		List<InteresDto> rs = controller.filterByType(listDto, Type.MONTH);
		assertEquals(true, rs.isEmpty());
	}
	
	@Test
	public void test4() {
		List<InteresDto> listDto = new ArrayList<>();
		InteresDto i0 = new InteresDto();
		i0.setId((long)4);
		i0.setNumber(9);
		i0.setRatio((float)0.5);
		i0.setType(Type.MONTH.toString());
		listDto.add(i0);
		
		InteresDto i1 = new InteresDto();
		i1.setId((long)0);
		i1.setNumber(0);
		i1.setRatio((float)0.3);
		i1.setType(Type.NOLIMIT.toString());
		listDto.add(i1);
		
		InteresDto i2 = new InteresDto();
		i2.setId((long)2);
		i2.setNumber(3);
		i2.setRatio((float)0.4);
		i2.setType(Type.MONTH.toString());
		listDto.add(i2);
		
		List<InteresDto> rs = controller.filterByType(listDto, Type.MONTH);
		assertEquals(2, rs.size());
	}
}
