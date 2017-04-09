package com.system.entity4Json;

import java.io.Serializable;

public class Paper4Json implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1902613820445758233L;

	/**
	 * 学生信息 id##name,id##name
	 */
	private String studentInfo;

	public String getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(String studentInfo) {
		this.studentInfo = studentInfo;
	}

	/**
	 * 人数是否已满
	 */
	private String maxNum;

	/**
	 * 选了题目变1，老师确认变2
	 */
	private Integer paperReview;

	public Integer getPaperReview() {
		return paperReview;
	}

	public void setPaperReview(Integer paperReview) {
		this.paperReview = paperReview;
	}

	private Integer id;

	private String title;

	private Integer grade;

	private String className;

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	private String teacherName;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * 教务审查,0为未过审查,1为过审,默认为0,只有过审才能被学生看到和选择
	 */
	private Integer titleReview;

	private Integer num;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getTitleReview() {
		return titleReview;
	}

	public void setTitleReview(Integer titleReview) {
		this.titleReview = titleReview;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(String maxNum) {
		this.maxNum = maxNum;
	}

}
