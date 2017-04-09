package com.system.dao.impl;

import java.util.ArrayList;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.system.dao.IStudentDao;
import com.system.entity.Student;
import com.system.util.StringUtil;

@Repository
public class StudentDao extends BaseDao<Student, Integer> implements
		IStudentDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getPageList(Student student, Integer page,
			Integer rows, Integer departmentId) {
		Session session = super.getSession();
		List<Student> students = new ArrayList<Student>();
		try {
			Criteria crit = session.createCriteria(Student.class);
			if (student != null && student.getAccount() != null
					&& !StringUtil.isNullOrEmpty(student.getAccount())) {
				crit.add(Restrictions.like("account", student.getAccount()));
			}
			if (student != null && student.getName() != null
					&& !StringUtil.isNullOrEmpty(student.getName())) {
				crit.add(Restrictions.like("name", student.getName()));
			}
			if (student != null && student.getClasses() != null) {
				crit.add(Restrictions.like("classes.id", student.getClasses()
						.getId()));
			}
			crit.setFirstResult((page - 1) * rows);
			crit.setMaxResults(rows);
			students = crit.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getPageTotal(Student student, Integer departmentId) {
		Integer total = 0;
		Session session = super.getSession();
		List<Student> students = new ArrayList<Student>();
		try {
			Criteria crit = session.createCriteria(Student.class);
			if (student != null && student.getAccount() != null
					&& !StringUtil.isNullOrEmpty(student.getAccount())) {
				crit.add(Restrictions.like("account", student.getAccount()));
			}
			if (student != null && student.getName() != null
					&& !StringUtil.isNullOrEmpty(student.getName())) {
				crit.add(Restrictions.like("name", student.getName()));
			}
			if (student != null && student.getClasses() != null) {
				crit.add(Restrictions.like("classes.id", student.getClasses()
						.getId()));
			}
			students = crit.list();
			if (students != null && !students.isEmpty()) {
				total = students.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getStudentsByDepartment(Integer id) {
		List<Student> students = new ArrayList<Student>();
		Session session = super.getSession();
		try {
			Criteria crit = session.createCriteria(Student.class);
			if (id != null) {
				crit.add(Restrictions.eq("department.id", id));
			}
			crit.add(Restrictions.eq("group.id", null));
			students = crit.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getStudentsByClassesId(Integer classesId) {
		List<Student> students = new ArrayList<Student>();
		Session session = super.getSession();
		try {
			Criteria crit = session.createCriteria(Student.class);
			if (classesId != null) {
				crit.add(Restrictions.eq("classes.id", classesId));
			}
			students = crit.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}

	/*
	 * @Override public List<Paper> getTopics(Student student) { Session session
	 * = super.getSession(); List<Paper> papers = null;
	 * 
	 * student.getClasses().get
	 * 
	 * try { Criteria crit = session.createCriteria(Paper.class);
	 * 
	 * } catch (Exception e) { }
	 * 
	 * return null; }
	 */

}
