package com.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.entity.AccountEntity;

public interface AccountRepository extends CrudRepository<AccountEntity, Long>{
	@Query("Select ae from AccountEntity ae Where ae.numberAccount=?1")
	public AccountEntity findByNumberAccount(String number);
}
