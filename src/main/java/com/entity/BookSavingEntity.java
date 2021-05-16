package com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="book_saving")
public class BookSavingEntity extends BaseEntity{	
	@Column
	private Date startDate;
	
	@Column
	private Long amountSend;
	
	@Column
	private String description;
	
	@Column
	private Date withdrawDate;
	
	@ManyToOne
	@JoinColumn(name="interes_id")
	private InteresEntity interes;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private AccountEntity account;
	
	@PrePersist
	private void setStartDate() {
		if(this.startDate == null) this.startDate = new Date();
	}

	public BookSavingEntity() {
		super();
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Long getAmountSend() {
		return amountSend;
	}

	public void setAmountSend(Long amountSend) {
		this.amountSend = amountSend;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public InteresEntity getInteres() {
		return interes;
	}

	public void setInteres(InteresEntity interes) {
		this.interes = interes;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public Date getWithdrawDate() {
		return withdrawDate;
	}

	public void setWithdrawDate(Date withdrawDate) {
		this.withdrawDate = withdrawDate;
	}

}
