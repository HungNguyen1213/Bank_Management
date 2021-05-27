package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.controller.CustomerController;
import com.converter.AccountConverter;
import com.converter.BookSavingConverter;
import com.dto.AccountDto;
import com.dto.BookSavingDto;
import com.repository.AccountRepository;
import com.repository.BookSavingRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestHandleShowCustomerDetail {
	
	private CustomerController cc = new CustomerController();
	
	@Autowired
	public AccountRepository accRepo;
	
	@Autowired
	public BookSavingRepository bsRepo;
	
	@Test
	public void test3() {
		AccountConverter ac = new AccountConverter();
		BookSavingConverter bsc = new BookSavingConverter();
		Map<String, Object> rs = cc.handleShowCustomerDetail((long)1, accRepo, ac, bsRepo, bsc);
		AccountDto rsAcc = (AccountDto) rs.get("acc");
		@SuppressWarnings("unchecked")
		List<BookSavingDto> listBs = (List<BookSavingDto>) rs.get("listBs");
		assertEquals((long)88222222, rsAcc.getBalance());
		assertEquals("306357", rsAcc.getNumberAccount());
		assertEquals("jinylove", rsAcc.getUsername());
		assertEquals(null, listBs);
	}
	
	@Test
	public void test4() {
		AccountConverter ac = new AccountConverter();
		BookSavingConverter bsc = new BookSavingConverter();
		Map<String, Object> rs = cc.handleShowCustomerDetail((long)2, accRepo, ac, bsRepo, bsc);
		AccountDto rsAcc = (AccountDto) rs.get("acc");
		@SuppressWarnings("unchecked")
		List<BookSavingDto> listBs = (List<BookSavingDto>) rs.get("listBs");
		assertEquals((long)88222222, rsAcc.getBalance());
		assertEquals("943253", rsAcc.getNumberAccount());
		assertEquals("huy", rsAcc.getUsername());
		assertEquals(1, listBs.size());
	}
	
	@Test
	public void test5() {
		AccountConverter ac = new AccountConverter();
		BookSavingConverter bsc = new BookSavingConverter();
		Map<String, Object> rs = cc.handleShowCustomerDetail((long)4, accRepo, ac, bsRepo, bsc);
		AccountDto rsAcc = (AccountDto) rs.get("acc");
		@SuppressWarnings("unchecked")
		List<BookSavingDto> listBs = (List<BookSavingDto>) rs.get("listBs");
		assertEquals((long)81548890, rsAcc.getBalance());
		assertEquals("541816", rsAcc.getNumberAccount());
		assertEquals("khai", rsAcc.getUsername());
		assertEquals(3, listBs.size());
	}
	
	@Test
	public void test6() {
		AccountConverter ac = new AccountConverter();
		BookSavingConverter bsc = new BookSavingConverter();
		Map<String, Object> rs = cc.handleShowCustomerDetail((long)69, accRepo, ac, bsRepo, bsc);
		assertEquals(null, rs);
	}
}
