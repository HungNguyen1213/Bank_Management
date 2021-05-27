package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.controller.CustomerController;
import com.converter.AccountConverter;
import com.dto.AccountDto;
import com.repository.AccountRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestHandleSearchCustomer {
	@Autowired
	private AccountRepository accRepo;
	
	private CustomerController cc = new CustomerController();
	
	@Test
	public void testSearchValid() {
		AccountConverter ac = new AccountConverter();
		String key = "306357";
		Map<String, Object> rs = cc.handleSearchCustomer(key, accRepo, ac);
		AccountDto rsAcc = (AccountDto) rs.get("acc");
		assertEquals("Nguyen Huy Hung", rsAcc.getCustomer().getName());
		assertEquals("0364441092", rsAcc.getCustomer().getPhone());
		assertEquals((long)88222222, rsAcc.getBalance());
		assertEquals(true, rs.get("isSuccess"));
	}
	
	@Test
	public void testSearchInValid() {
		AccountConverter ac = new AccountConverter();
		String key = "12324";
		Map<String, Object> rs = cc.handleSearchCustomer(key, accRepo, ac);
		assertEquals(null, rs.get("acc"));
		assertEquals(false, rs.get("isSuccess"));
	}
}
