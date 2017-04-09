package com.system.entity4Json;

import java.io.Serializable;

public class Classes4Json implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1331333349484905157L;

	private Integer id;

	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	private String department;

}
