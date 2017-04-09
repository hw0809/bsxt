package com.system.entity4Json;

import java.io.Serializable;

public class TeacherInfo4Json implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1499075309008889492L;

	private String account;

	private String departmentName;

	private String desc;

	private String email;

	private Integer id;

	private String name;

	private String tel;

	public String getAccount() {
		return account;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public String getDesc() {
		return desc;
	}

	public String getEmail() {
		return email;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTel() {
		return tel;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
