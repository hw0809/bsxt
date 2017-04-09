package com.system.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.system.entity.impl.IEntity;
import com.system.serializer.TeacherSerializer;

@Table(name = "t_paper")
@Entity
public class Paper implements IEntity<Integer> {

	// 一个班级有多篇论文

	/**
	 * 
	 */
	private static final long serialVersionUID = -5370716285677674854L;
	
	@Column(name = "F_DESC")
	private String desc;

	/**
	 * 年份不同的题目可以相同
	 */
	@Column(name = "F_GRADE")
	private Integer grade;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "F_ID")
	private Integer id;

	/**
	 * 选题人数
	 */
	@Column(name = "F_NUM")
	private Integer num;

	/**
	 * 学生选题审查,0为未过审查,1为过审,默认为0,过审表示学生选择此题
	 */
	@Column(name = "F_STUDENTREVIEW")
	private Integer studentReview;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "t_paper_classes", joinColumns = { @JoinColumn(name = "F_PAPERID") }, inverseJoinColumns = { @JoinColumn(name = "F_CLASSESID") })
	private Set<Classes> classes;

	public Set<Classes> getClasses() {
		return classes;
	}

	public void setClasses(Set<Classes> classes) {
		this.classes = classes;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "paper")
	private List<Student> students;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "F_TEACHERID")
	private Teacher teacher;

	@Column(name = "F_TITLE")
	private String title;

	/**
	 * 教务审查,0为未过审查,1为过审,2为不通过,默认为0,只有过审才能被学生看到和选择
	 */
	@Column(name = "F_REVIEW")
	private Integer titleReview;

	/**
	 * 教务员评价
	 */
	@Column(name = "F_APPRAISE")
	private String apperise;

	public String getApperise() {
		return apperise;
	}

	public void setApperise(String apperise) {
		this.apperise = apperise;
	}

	@JsonSerialize(using = TeacherSerializer.class)
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getDesc() {
		return desc;
	}

	public Integer getGrade() {
		return grade;
	}

	public Integer getId() {
		return id;
	}

	public Integer getNum() {
		return num;
	}

	public Integer getStudentReview() {
		return studentReview;
	}

	public List<Student> getStudents() {
		return students;
	}

	public String getTitle() {
		return title;
	}

	public Integer getTitleReview() {
		return titleReview;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public void setStudentReview(Integer studentReview) {
		this.studentReview = studentReview;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTitleReview(Integer titleReview) {
		this.titleReview = titleReview;
	}

}
