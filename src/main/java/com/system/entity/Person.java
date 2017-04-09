package com.system.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.system.entity.impl.IEntity;

@Table(name = "t_person")
@Entity
public class Person implements IEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 871657262670167203L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "F_ID")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "F_NAME")
	private String name;

	public Set<Hourse> getHourses() {
		return hourses;
	}

	public void setHourses(Set<Hourse> hourses) {
		this.hourses = hourses;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "t_hourse_person", joinColumns = { @JoinColumn(name = "f_personid") }, inverseJoinColumns = { @JoinColumn(name = "f_hourseid") })
	private Set<Hourse> hourses;

}
