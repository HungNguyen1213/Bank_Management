package com.converter;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dto.BookSavingDto;
import com.entity.BookSavingEntity;

@Component
public class BookSavingConverter {
	@Autowired
	private InteresConverter ic;
	public BookSavingDto toDto(BookSavingEntity bse) {
		BookSavingDto b = new BookSavingDto();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		b.setAmountSend(bse.getAmountSend());
		b.setDescription(bse.getDescription());
		b.setId(bse.getId());
		b.setInteres(ic.toDto(bse.getInteres()));
		b.setStartDate(formatter.format(bse.getStartDate()));
		if(bse.getWithdrawDate() != null) {
			b.setWithdrawDate(formatter.format(bse.getWithdrawDate()));
		}
		return b;
	}
}
