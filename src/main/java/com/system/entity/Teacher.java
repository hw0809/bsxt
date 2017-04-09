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
import com.system.serializer.DepartmentSerializer;

@Table(name = "T_Teacher")
@Entity
public class Teacher implements IEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7255791629532841452L;

	@Column(name = "F_ACCOUNT")
	private String account;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "F_DEPARTMENTID")
	@JsonSerialize(using = DepartmentSerializer.class)
	private Department department;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "t_group_teacher", joinColumns = { @JoinColumn(name = "F_TEACHERID") }, inverseJoinColumns = { @JoinColumn(name = "F_GROUPID") })
	private Set<Group> groups;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "t_review_group_teacher", joinColumns = { @JoinColumn(name = "F_TEACHERID") }, inverseJoinColumns = { @JoinColumn(name = "F_REVIEWGROUPID") })
	private Set<ReviewTeacherGroup> reviewTeacherGroups;

	public Set<Group> getGroups() {
		return groups;
	}

	public Set<ReviewTeacherGroup> getReviewTeacherGroups() {
		return reviewTeacherGroups;
	}

	public void setReviewTeacherGroups(
			Set<ReviewTeacherGroup> reviewTeacherGroups) {
		this.reviewTeacherGroups = reviewTeacherGroups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	@Column(name = "F_DESC")
	private String desc;

	@Column(name = "F_EMAIL")
	private String email;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "F_ID")
	private Integer id;

	@Column(name = "F_NAME")
	private String name;

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "teacher")
	private List<Student> students;

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "teacher")
	private List<Paper> papers;

	@Column(name = "F_TEL")
	private String tel;

	public List<Paper> getPapers() {
		return papers;
	}

	public void setPapers(List<Paper> papers) {
		this.papers = papers;
	}

	public String getAccount() {
		return account;
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

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Student> getStudents() {
		return students;
	}

	public String getTel() {
		return tel;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
