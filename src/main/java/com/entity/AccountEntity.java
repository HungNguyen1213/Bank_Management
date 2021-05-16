package com.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class AccountEntity extends BaseEntity{
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false, unique = true)
	private String numberAccount;
	
	@Column
	private Long balance;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private CustomerEntity customer;
	
	@OneToMany(mappedBy = "account")
	private List<BookSavingEntity> ListbookSaving;

	public AccountEntity() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(String numberAccount) {
		this.numberAccount = numberAccount;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public List<BookSavingEntity> getListbookSaving() {
		return ListbookSaving;
	}

	public void setListbookSaving(List<BookSavingEntity> listbookSaving) {
		ListbookSaving = listbookSaving;
	}
	
}
