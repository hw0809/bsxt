package com.system.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.system.entity.impl.IEntity;

@Table(name = "T_LoginInformation")
@Entity
public class LoginInformation implements IEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2063757780681746245L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "F_ID")
	private Integer id;

	/**
	 * account对应student,teacher表中的学号,工号
	 */
	@Column(name = "F_ACCOUNT")
	private String account;

	@Column(name = "F_PASSWORD")
	private String password;

	/**
	 * 区别身份 0--教务员,1--教师,2--学生
	 */
	@Column(name = "F_LOGINTYPE")
	private Integer loginType;

	public Integer getId() {
		return id;
	}

	public Integer getLoginType() {
		return loginType;
	}

	public String getPassword() {
		return password;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
