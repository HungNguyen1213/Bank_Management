package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.controller.BookSavingController;
import com.converter.AccountConverter;
import com.converter.BookSavingConverter;
import com.dto.AccountDto;
import com.dto.BookSavingDto;
import com.repository.BookSavingRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestHandleShowWithdrawInfomation {
	private BookSavingController controller = new BookSavingController();
	@Autowired
	private BookSavingRepository bsRepo;
	
	@Test
	public void test19() {
		BookSavingConverter bc = new BookSavingConverter();
		AccountConverter ac = new AccountConverter();
		Map<String, Object> rs = controller.handleShowWithdrawInfomation((long)4, bsRepo, bc, ac);
		BookSavingDto bs = (BookSavingDto) rs.get("bs");
		assertEquals("Buy Smart Phone", bs.getDescription());
		assertEquals((long)6666, bs.getAmountSend());
		assertEquals("MONTH", bs.getInteres().getType());
		AccountDto acc = (AccountDto) rs.get("acc");
		assertEquals("Duong Quoc Khai", acc.getCustomer().getName());
	}
	
	@Test
	public void test20() {
		BookSavingConverter bc = new BookSavingConverter();
		AccountConverter ac = new AccountConverter();
		Map<String, Object> rs = controller.handleShowWithdrawInfomation((long)69, bsRepo, bc, ac);
		assertNull(rs);
	}
}
