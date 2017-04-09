package com.system.entity;

import java.util.List;

import javax.persistence.Column;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.system.entity.impl.IEntity;

@Table(name = "T_DEPARTMENT")
@Entity
public class Department implements IEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7427540754498223054L;

	// mappedBy表示被维护端
	@OneToOne(mappedBy = "department")
	private Group group;

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "department")
	private List<Classes> classes;

	@Column(name = "F_DEPARTMENTCODE")
	private Integer departmentCode;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "F_ID")
	private Integer id;

	@Column(name = "F_NAME")
	private String name;

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "department")
	private List<Student> students;

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "department")
	private List<Teacher> teachers;

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "department")
	private List<Adminstrator> adminstrators;

	public List<Adminstrator> getAdminstrators() {
		return adminstrators;
	}

	public void setAdminstrators(List<Adminstrator> adminstrators) {
		this.adminstrators = adminstrators;
	}

	public List<Classes> getClasses() {
		return classes;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Integer getDepartmentCode() {
		return departmentCode;
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

	public void setClasses(List<Classes> classes) {
		this.classes = classes;
	}

	public void setDepartmentCode(Integer departmentCode) {
		this.departmentCode = departmentCode;
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
