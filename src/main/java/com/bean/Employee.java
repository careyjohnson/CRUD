package com.bean;

import java.util.Date;

public class Employee {
	private int id;
	private String code;
	private String name;
	private Date birthday;
	private String address;
	private String gender;
	private Position positionId;
	private String status;
	
	
	public Employee() {
	}


	public Employee(int id, String code, String name, Date birthday, String address, String gender, Position positionId,
			String status) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.birthday = birthday;
		this.address = address;
		this.gender = gender;
		this.positionId = positionId;
		this.status = status;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public Position getPositionId() {
		return positionId;
	}


	public void setPositionId(Position positionId) {
		this.positionId = positionId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
}
