package com.system.entity4Json;

import java.io.Serializable;

public class Teacher4Json implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2541449935104149965L;

	private Integer id;

	private String account;

	private String name;

	private String department;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
