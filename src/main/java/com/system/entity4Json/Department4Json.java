package com.system.entity4Json;

import java.io.Serializable;

public class Department4Json implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3220394459978539958L;

	private Integer departmentCode;

	private String name;

	public Integer getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(Integer departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
