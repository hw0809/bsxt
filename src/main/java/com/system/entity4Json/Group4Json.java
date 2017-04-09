package com.system.entity4Json;

import java.io.Serializable;

public class Group4Json implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6423520176984491186L;

	private Integer id;

	/**
	 * 学生信息 id##name
	 */
	private String studentInfo;

	/**
	 * 分组名称
	 */
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(String studentInfo) {
		this.studentInfo = studentInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeacherInfo() {
		return teacherInfo;
	}

	public void setTeacherInfo(String teacherInfo) {
		this.teacherInfo = teacherInfo;
	}

	/**
	 * 教师信息id##name
	 */
	private String teacherInfo;
}
