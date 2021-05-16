package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.entity.BookSavingEntity;

public interface BookSavingRepository extends CrudRepository<BookSavingEntity, Long>{
	@Query("Select bse from BookSavingEntity bse Where bse.account.id=?1 And bse.withdrawDate=null")
	List<BookSavingEntity> findByAccountId(Long accId);
}
