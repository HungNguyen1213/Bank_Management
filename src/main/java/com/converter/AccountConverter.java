package com.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dto.AccountDto;
import com.entity.AccountEntity;

@Component
public class AccountConverter {
	@Autowired
	private CustomerConverter cc;
	public AccountDto toDto(AccountEntity ae) {
		AccountDto a = new AccountDto();
		a.setBalance(ae.getBalance());
		a.setCustomer(cc.toDto(ae.getCustomer()));
		a.setId(ae.getId());
		a.setNumberAccount(ae.getNumberAccount());
		a.setPassword(ae.getPassword());
		a.setUsername(ae.getUsername());
		return a;
	}
}
