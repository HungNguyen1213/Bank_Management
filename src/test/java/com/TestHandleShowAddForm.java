package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.controller.BookSavingController;
import com.converter.AccountConverter;
import com.converter.InteresConverter;
import com.dto.AccountDto;
import com.dto.InteresDto;
import com.entity.InteresEntity.Type;
import com.repository.AccountRepository;
import com.repository.InteresRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestHandleShowAddForm {
	
	private BookSavingController controller = new BookSavingController();
	@Autowired
	private InteresRepository iRepo;
	@Autowired
	private AccountRepository accRepo;
	
	@Test
	public void test8() {
		InteresConverter ic = new InteresConverter();
		AccountConverter ac = new AccountConverter();
		Map<String, Object> rs = controller.handleShowAddForm((long)1, iRepo, accRepo, ic, ac);
		@SuppressWarnings("unchecked")
		List<InteresDto> list = (List<InteresDto>)rs.get(Type.MONTH.toString().toLowerCase());
		assertEquals(0, list.size());
	}
	
	@Test
	public void test9() {
		InteresConverter ic = new InteresConverter();
		AccountConverter ac = new AccountConverter();
		Map<String, Object> rs = controller.handleShowAddForm((long)1, iRepo, accRepo, ic, ac);
		@SuppressWarnings("unchecked")
		List<InteresDto> list = (List<InteresDto>)rs.get(Type.MONTH.toString().toLowerCase());
		assertEquals(0, list.size());
	}
	
	@Test
	public void test10() {
		InteresConverter ic = new InteresConverter();
		AccountConverter ac = new AccountConverter();
		Map<String, Object> rs = controller.handleShowAddForm((long)1, iRepo, accRepo, ic, ac);
		@SuppressWarnings("unchecked")
		List<InteresDto> list = (List<InteresDto>)rs.get(Type.MONTH.toString().toLowerCase());
		assertEquals(3, list.size());
	}
	
	@Test
	public void test11() {
		InteresConverter ic = new InteresConverter();
		AccountConverter ac = new AccountConverter();
		Map<String, Object> rs = controller.handleShowAddForm((long)1, iRepo, accRepo, ic, ac);
		@SuppressWarnings("unchecked")
		List<InteresDto> listM = (List<InteresDto>)rs.get(Type.MONTH.toString().toLowerCase());
		
		@SuppressWarnings("unchecked")
		List<InteresDto> listY = (List<InteresDto>)rs.get(Type.YEAR.toString().toLowerCase());
		
		@SuppressWarnings("unchecked")
		List<InteresDto> listN = (List<InteresDto>)rs.get(Type.NOLIMIT.toString().toLowerCase());
		assertEquals(0, listN.size() + listY.size() + listM.size());
	}
	
	@Test
	public void test12() {
		InteresConverter ic = new InteresConverter();
		AccountConverter ac = new AccountConverter();
		Map<String, Object> rs = controller.handleShowAddForm((long)1, iRepo, accRepo, ic, ac);
		@SuppressWarnings("unchecked")
		List<InteresDto> listM = (List<InteresDto>)rs.get(Type.MONTH.toString().toLowerCase());
		
		@SuppressWarnings("unchecked")
		List<InteresDto> listY = (List<InteresDto>)rs.get(Type.YEAR.toString().toLowerCase());
		
		@SuppressWarnings("unchecked")
		List<InteresDto> listN = (List<InteresDto>)rs.get(Type.NOLIMIT.toString().toLowerCase());
		assertEquals(1, listN.size() + listY.size() + listM.size());
	}
	
	@Test
	public void test13() {
		InteresConverter ic = new InteresConverter();
		AccountConverter ac = new AccountConverter();
		Map<String, Object> rs = controller.handleShowAddForm((long)1, iRepo, accRepo, ic, ac);
		@SuppressWarnings("unchecked")
		List<InteresDto> listM = (List<InteresDto>)rs.get(Type.MONTH.toString().toLowerCase());
		
		@SuppressWarnings("unchecked")
		List<InteresDto> listY = (List<InteresDto>)rs.get(Type.YEAR.toString().toLowerCase());
		
		@SuppressWarnings("unchecked")
		List<InteresDto> listN = (List<InteresDto>)rs.get(Type.NOLIMIT.toString().toLowerCase());
		assertEquals(7, listN.size() + listY.size() + listM.size());
	}
	
	@Test
	public void test14() {
		InteresConverter ic = new InteresConverter();
		AccountConverter ac = new AccountConverter();
		Map<String, Object> rs = controller.handleShowAddForm((long)1, iRepo, accRepo, ic, ac);
		AccountDto rsAcc = (AccountDto) rs.get("acc");
		assertEquals("Nguyen Huy Hung", rsAcc.getCustomer().getName());
		assertEquals("jinylove", rsAcc.getUsername());
	}
	
	@Test
	public void test15() {
		InteresConverter ic = new InteresConverter();
		AccountConverter ac = new AccountConverter();
		Map<String, Object> rs = controller.handleShowAddForm((long)69, iRepo, accRepo, ic, ac);
		AccountDto rsAcc = (AccountDto) rs.get("acc");
		assertEquals(null, rsAcc);
	}
}
