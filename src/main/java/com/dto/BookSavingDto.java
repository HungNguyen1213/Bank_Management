package com.dto;

public class BookSavingDto {
	private Long id;
	private String startDate;
	private String description;
	private Long amountSend;
	private String withdrawDate;
	private InteresDto interes;
	
	public BookSavingDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getAmountSend() {
		return amountSend;
	}

	public void setAmountSend(Long amountSend) {
		this.amountSend = amountSend;
	}

	public InteresDto getInteres() {
		return interes;
	}
	public void setInteres(InteresDto interes) {
		this.interes = interes;
	}

	public String getWithdrawDate() {
		return withdrawDate;
	}

	public void setWithdrawDate(String withdrawDate) {
		this.withdrawDate = withdrawDate;
	}
	
}
