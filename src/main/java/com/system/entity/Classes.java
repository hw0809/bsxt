package com.system.entity;

import java.util.List;

import java.util.Set;

import javax.persistence.Column;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.system.entity.impl.IEntity;
import com.system.serializer.DepartmentSerializer;

@Table(name = "T_CLASSES")
@Entity
public class Classes implements IEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6951839598864064623L;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "F_DEPARTMENTID")
	@JsonSerialize(using = DepartmentSerializer.class)
	private Department department;

	// 一个论文题目对应一个班级,但是可能会有web1班web2班,所以一对多
	// 一个班级也有多个论文题目,所以是多对多
	/*
	 * @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 * //@ManyToOne(cascade = , fetch = FetchType.LAZY)
	 * //@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	 * 
	 * @JoinColumn(name = "F_PAPERID") private Paper paper;
	 * 
	 * public Paper getPaper() { return paper; }
	 * 
	 * public void setPaper(Paper paper) { this.paper = paper; }
	 */

	@ManyToMany(mappedBy = "classes")
	private Set<Paper> papers;

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@Column(name = "F_GRADE")
	private Integer grade;

	public Set<Paper> getPapers() {
		return papers;
	}

	public void setPapers(Set<Paper> papers) {
		this.papers = papers;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "F_ID")
	private Integer id;

	@Column(name = "F_NAME")
	private String name;

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "classes")
	private List<Student> students;

	public Department getDepartment() {
		return department;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

}
