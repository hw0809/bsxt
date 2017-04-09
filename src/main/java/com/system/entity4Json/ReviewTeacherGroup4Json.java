package com.system.entity4Json;

import java.io.Serializable;

public class ReviewTeacherGroup4Json implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9098873002940074199L;

	private Integer id;

	/**
	 * 学生信息 id##name
	 */
	private String studentInfo;

	/**
	 * 教师信息id##name
	 */
	private String teacherInfo;

	public Integer getId() {
		return id;
	}

	public String getStudentInfo() {
		return studentInfo;
	}

	public String getTeacherInfo() {
		return teacherInfo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStudentInfo(String studentInfo) {
		this.studentInfo = studentInfo;
	}

	public void setTeacherInfo(String teacherInfo) {
		this.teacherInfo = teacherInfo;
	}
}
