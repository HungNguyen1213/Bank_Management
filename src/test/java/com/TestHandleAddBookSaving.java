package com;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.controller.BookSavingController;
import com.entity.BookSavingEntity;
import com.repository.AccountRepository;
import com.repository.BookSavingRepository;
import com.repository.InteresRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestHandleAddBookSaving {
	private BookSavingController controller = new BookSavingController();
	@Autowired
	private InteresRepository iRepo;
	@Autowired
	private AccountRepository accRepo;
	@Autowired
	private BookSavingRepository bsRepo;
	
	@Test
	@Rollback(true)
	public void test16() {
		BookSavingEntity rs = controller.handleAddBookSaving("1", "66666", "2", "Buy Villar", iRepo, accRepo, bsRepo);
		assertNotNull(rs);
	}
	
	@Test
	@Rollback(true)
	public void test17() {
		BookSavingEntity rs = controller.handleAddBookSaving("2", "66666", "69", "Buy Villar", iRepo, accRepo, bsRepo);
		assertNull(rs);
	}
	
	@Test
	@Rollback(true)
	public void test18() {
		BookSavingEntity rs = controller.handleAddBookSaving("69", "66666", "5", "Buy Villar", iRepo, accRepo, bsRepo);
		assertNull(rs);
	}
}
