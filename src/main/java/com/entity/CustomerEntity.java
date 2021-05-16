package com.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity{
	@Column
	private String name;
	
	@Column
	private Date birthday;
	
	@Column
	private String phone;
	
	@Column
	private String email;
	
	@Column
	private String permanentAddress;
	
	@Column
	private String homeTown;
	
	@OneToMany(mappedBy = "customer")
	private List<AccountEntity> listAccount;

	public CustomerEntity() {
		super();
	}

	public List<AccountEntity> getListAccount() {
		return listAccount;
	}

	public void setListAccount(List<AccountEntity> listAccount) {
		this.listAccount = listAccount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}	
}
