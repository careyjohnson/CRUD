package com.bean;

import java.util.Date;

public class Employee {
	protected int id;
	protected String code;
	protected String name;
	protected Date birthday;
	protected String address;
	protected String gender;
	protected String position;
	protected String status;
	
	public Employee() {
	}

	public Employee(int id, String code, String name, Date birthday, String address, String gender, String position, 
			String status) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.birthday = birthday;
		this.address = address;
		this.gender = gender;
		this.position = position;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
