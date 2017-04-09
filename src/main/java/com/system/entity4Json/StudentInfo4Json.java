package com.system.entity4Json;

import java.io.Serializable;

public class StudentInfo4Json implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3385266797098264271L;

	/**
	 * 账号
	 */
	private String account;
	
	/**
	 * 简介
	 */
	private String desc;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 班级名称
	 */
	private String className;

	/**
	 * 系名称
	 */
	private String departmentName;

	private String email;

	/**
	 * 年级
	 */
	private Integer grade;

	private Integer id;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 指导老师
	 */
	private String teacherName;

	private String tel;

	public String getAccount() {
		return account;
	}

	public String getClassName() {
		return className;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public String getEmail() {
		return email;
	}

	public Integer getGrade() {
		return grade;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public String getTel() {
		return tel;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}
