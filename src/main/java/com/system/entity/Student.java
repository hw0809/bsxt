package com.system.entity;

import javax.persistence.Column;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.system.entity.impl.IEntity;
import com.system.serializer.DepartmentSerializer;
import com.system.serializer.StudentSerializer;

@Table(name = "T_Student")
@Entity
public class Student implements IEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -329408099815958574L;

	@Column(name = "F_GROUPEVALUATE")
	private String groupEvaluate;

	public Integer getGroupScore() {
		return groupScore;
	}

	public String getGroupEvaluate() {
		return groupEvaluate;
	}

	public void setGroupEvaluate(String groupEvaluate) {
		this.groupEvaluate = groupEvaluate;
	}

	public void setGroupScore(Integer groupScore) {
		this.groupScore = groupScore;
	}

	@Column(name = "F_GROUPSCORE")
	private Integer groupScore;

	/**
	 * 阅卷老师给分
	 */
	@Column(name = "F_REVIEWSCORE")
	private Integer reviewScore;

	public Integer getReviewScore() {
		return reviewScore;
	}

	public void setReviewScore(Integer reviewScore) {
		this.reviewScore = reviewScore;
	}

	public String getReviewEvaluate() {
		return reviewEvaluate;
	}

	public void setReviewEvaluate(String reviewEvaluate) {
		this.reviewEvaluate = reviewEvaluate;
	}

	/**
	 * 阅卷老师评卷
	 */
	@Column(name = "F_REVIEWEVALUATE")
	private String reviewEvaluate;

	@Column(name = "F_ACCOUNT")
	private String account;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "F_CLASSESID")
	private Classes classes;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "F_DEPARTMENTID")
	@JsonSerialize(using = DepartmentSerializer.class)
	private Department department;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "F_GROUPID")
	private Group group;

	/**
	 * 评阅小组
	 */
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "F_REVIEWTEACHERGROUPID")
	private ReviewTeacherGroup reviewTeacherGroup;

	public Group getGroup() {
		return group;
	}

	public ReviewTeacherGroup getReviewTeacherGroup() {
		return reviewTeacherGroup;
	}

	public void setReviewTeacherGroup(ReviewTeacherGroup reviewTeacherGroup) {
		this.reviewTeacherGroup = reviewTeacherGroup;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	/**
	 * 简介
	 */
	@Column(name = "F_DESC")
	private String desc;

	@Column(name = "F_EMAIL")
	private String email;

	@Column(name = "F_GRADE")
	private Integer grade;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "F_ID")
	private Integer id;

	/**
	 * 中期检查url
	 */
	@Column(name = "F_INSPECTION")
	private String inspection;

	/**
	 * 中期检查评价
	 */
	@Column(name = "F_INSPECTIONEVALUATE")
	private String inspectionEvaluate;

	@Column(name = "F_INSPECTIONTITLE")
	private String inspectionTitle;

	public String getInspectionTitle() {
		return inspectionTitle;
	}

	public void setInspectionTitle(String inspectionTitle) {
		this.inspectionTitle = inspectionTitle;
	}

	public String getThesisProposalTitle() {
		return thesisProposalTitle;
	}

	public void setThesisProposalTitle(String thesisProposalTitle) {
		this.thesisProposalTitle = thesisProposalTitle;
	}

	public String getThesisTitle() {
		return thesisTitle;
	}

	public void setThesisTitle(String thesisTitle) {
		this.thesisTitle = thesisTitle;
	}

	@Column(name = "F_NAME")
	private String name;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "F_PAPERID")
	private Paper paper;

	/**
	 * 选了题目变1，老师确认变2,0什么都没选,4是人数已满并且是自己选的
	 */
	@Column(name = "F_PAPERREVIEW")
	private Integer paperReview;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "F_TEACHERID")
	@JsonSerialize(using = StudentSerializer.class)
	private Teacher teacher;

	@Column(name = "F_TEL")
	private String tel;

	/**
	 * 毕业论文url
	 */
	@Column(name = "F_THESIS")
	private String thesis;

	/**
	 * 毕业论文评价
	 */
	@Column(name = "F_THESISEVALUATE")
	private String thesisEvaluate;

	public String getInspectionEvaluate() {
		return inspectionEvaluate;
	}

	public void setInspectionEvaluate(String inspectionEvaluate) {
		this.inspectionEvaluate = inspectionEvaluate;
	}

	public String getThesisEvaluate() {
		return thesisEvaluate;
	}

	public void setThesisEvaluate(String thesisEvaluate) {
		this.thesisEvaluate = thesisEvaluate;
	}

	public String getThesisProposalEvaluate() {
		return thesisProposalEvaluate;
	}

	public void setThesisProposalEvaluate(String thesisProposalEvaluate) {
		this.thesisProposalEvaluate = thesisProposalEvaluate;
	}

	/**
	 * 毕业论文分数
	 */
	@Column(name = "F_THESISSCORE")
	private Integer thesisScore;

	public Integer getThesisScore() {
		return thesisScore;
	}

	public void setThesisScore(Integer thesisScore) {
		this.thesisScore = thesisScore;
	}

	/**
	 * 开题报告评价
	 */
	@Column(name = "F_THESISPROPOSALEVALUATE")
	private String thesisProposalEvaluate;

	/**
	 * 开题报告url
	 */
	@Column(name = "F_THESISPROPOSAL")
	private String thesisProposal;

	/**
	 * 开题报告标题
	 */
	@Column(name = "F_THESISPROPOSALTITLE")
	private String thesisProposalTitle;

	/**
	 * 毕业论文标题
	 */
	@Column(name = "F_THESISTITLE")
	private String thesisTitle;

	public String getAccount() {
		return account;
	}

	public Classes getClasses() {
		return classes;
	}

	public Department getDepartment() {
		return department;
	}

	public String getDesc() {
		return desc;
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

	public String getInspection() {
		return inspection;
	}

	public String getName() {
		return name;
	}

	public Paper getPaper() {
		return paper;
	}

	public Integer getPaperReview() {
		return paperReview;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public String getTel() {
		return tel;
	}

	public String getThesis() {
		return thesis;
	}

	public String getThesisProposal() {
		return thesisProposal;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public void setInspection(String inspection) {
		this.inspection = inspection;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public void setPaperReview(Integer paperReview) {
		this.paperReview = paperReview;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setThesis(String thesis) {
		this.thesis = thesis;
	}

	public void setThesisProposal(String thesisProposal) {
		this.thesisProposal = thesisProposal;
	}

}
