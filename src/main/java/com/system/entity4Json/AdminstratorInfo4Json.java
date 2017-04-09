package com.system.entity4Json;

import java.io.Serializable;

public class AdminstratorInfo4Json implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -100135073861623078L;

	private String name;

	private String account;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
