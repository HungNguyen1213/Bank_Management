package com;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.controller.CustomerController;
import com.entity.AccountEntity;
import com.entity.CustomerEntity;
import com.repository.AccountRepository;
import com.repository.CustomerRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestHandleCreateAccount {
	
	private CustomerController cc = new CustomerController();
	@Autowired
	private AccountRepository accRepo;
	@Autowired
	private CustomerRepository cusRepo;
	
	@Test
	@Rollback(true)
	public void test7() throws ParseException {
		AccountEntity accountPost = new AccountEntity();
		CustomerEntity cusE = new CustomerEntity();
		cusE.setEmail("congtm@gmail.com");
		cusE.setName("Tran Minh Cong");
		cusE.setPhone("0987321654");
		cusE.setPermanentAddress("Hoan Kiem");
		cusE.setHomeTown("Ha Nam");
		accountPost.setNumberAccount("678989");
		accountPost.setCustomer(cusE);
		accountPost.setUsername("congtm");
		accountPost.setPassword("123");
		AccountEntity rs = cc.handleCreateAccount("08/03/1999", "8888888", accountPost, cusRepo, accRepo);
		assertNotNull(rs);
	}
}
