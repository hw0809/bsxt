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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.system.entity.impl.IEntity;

@Table(name = "T_REVIEW_TEACHER_GROUP")
@Entity
public class ReviewTeacherGroup implements IEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2765900688471432832L;

	@OneToOne
	@JoinColumn(name = "F_DEPARTMENTID")
	private Department department;

	@Column(name = "F_GRADE")
	private Integer grade;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "F_ID")
	private Integer id;

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "reviewTeacherGroup")
	private List<Student> students;

	@ManyToMany(mappedBy = "reviewTeacherGroups")
	private Set<Teacher> teachers;

	public Department getDepartment() {
		return department;
	}

	public Integer getGrade() {
		return grade;
	}

	public Integer getId() {
		return id;
	}

	public List<Student> getStudents() {
		return students;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

}
