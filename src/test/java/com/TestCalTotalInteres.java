package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import com.controller.BookSavingController;
import com.entity.BookSavingEntity;
import com.entity.InteresEntity;
import com.entity.InteresEntity.Type;

public class TestCalTotalInteres {
	private BookSavingController controller = new BookSavingController();
	
	@Test
	public void test21() throws ParseException {
		InteresEntity i = new InteresEntity();
		i.setNumber(0);
		i.setRatio((float)0.3);
		i.setType(Type.NOLIMIT);
		BookSavingEntity bse = new BookSavingEntity();
		bse.setAmountSend((long)666666);
		bse.setInteres(i);
		String startDate = "20/04/2021";
		String endDate = "25/05/2021";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		bse.setStartDate(formatter.parse(startDate));
		long rs = controller.calTotalInteres(bse, formatter.parse(endDate));
		assertEquals((long)19178, rs);
	}
	
	@Test
	public void test22() throws ParseException {
		InteresEntity i = new InteresEntity();
		i.setNumber(6);
		i.setRatio((float)0.45);
		i.setType(Type.MONTH);
		BookSavingEntity bse = new BookSavingEntity();
		bse.setAmountSend((long)666666);
		bse.setInteres(i);
		String endDate = "25/05/2021";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		long rs = controller.calTotalInteres(bse, formatter.parse(endDate));
		assertEquals((long)150000, rs);
	}
	
	@Test
	public void test23() throws ParseException {
		InteresEntity i = new InteresEntity();
		i.setNumber(3);
		i.setRatio((float)0.65);
		i.setType(Type.YEAR);
		BookSavingEntity bse = new BookSavingEntity();
		bse.setAmountSend((long)666666);
		bse.setInteres(i);
		String endDate = "25/05/2021";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		long rs = controller.calTotalInteres(bse, formatter.parse(endDate));
		assertEquals((long)1299999, rs);
	}
}
