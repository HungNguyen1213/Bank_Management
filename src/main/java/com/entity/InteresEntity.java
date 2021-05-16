package com.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="interes")
public class InteresEntity extends BaseEntity{
	@Column
	private int number;
	
	@Column
	private float ratio;
	
	@Enumerated(EnumType.STRING)
	private Type type;
	
	public static enum Type {
		NOLIMIT, MONTH, YEAR
	}
	
	@OneToMany(mappedBy = "interes")
	private List<BookSavingEntity> listBookSaving;

	public InteresEntity() {
		super();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public float getRatio() {
		return ratio;
	}

	public void setRatio(float ratio) {
		this.ratio = ratio;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<BookSavingEntity> getListBookSaving() {
		return listBookSaving;
	}

	public void setListBookSaving(List<BookSavingEntity> listBookSaving) {
		this.listBookSaving = listBookSaving;
	}
	
	
}
