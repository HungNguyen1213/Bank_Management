package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.controller.BookSavingController;
import com.repository.BookSavingRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestHandleUpdateWithdraw {
	private BookSavingController controller = new BookSavingController();
	@Autowired
	private BookSavingRepository bsRepo;
	
	@Test
	@Rollback(true)
	public void test24() {
		String rs = controller.handleUpdateWithdraw((long)4, bsRepo);
		assertEquals("4", rs);
	}
	
	@Test
	@Rollback(true)
	public void test25() {
		String rs = controller.handleUpdateWithdraw((long)69, bsRepo);
		assertNull(rs);
	}
}
