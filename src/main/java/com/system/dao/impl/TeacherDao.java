package com.system.dao.impl;

import java.util.ArrayList;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.system.dao.ITeacherDao;
import com.system.entity.Teacher;
import com.system.util.StringUtil;

@Repository
public class TeacherDao extends BaseDao<Teacher, Integer> implements
		ITeacherDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> getPageList(Teacher teacher, Integer page,
			Integer rows, Integer departmentId) {
		Session session = super.getSession();
		List<Teacher> teachers = new ArrayList<Teacher>();
		try {
			Criteria crit = session.createCriteria(Teacher.class);
			if (teacher != null && teacher.getAccount() != null
					&& !StringUtil.isNullOrEmpty(teacher.getAccount())) {
				crit.add(Restrictions.like("account", teacher.getAccount()));
			}
			if (teacher != null && teacher.getName() != null
					&& !StringUtil.isNullOrEmpty(teacher.getName())) {
				crit.add(Restrictions.like("name", teacher.getName()));
			}
			crit.setFirstResult((page - 1) * rows);
			crit.setMaxResults(rows);
			teachers = crit.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teachers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getPageTotal(Teacher teacher, Integer departmentId) {
		Integer total = 0;
		Session session = super.getSession();
		List<Teacher> teachers = new ArrayList<Teacher>();
		try {
			Criteria crit = session.createCriteria(Teacher.class);
			if (teacher != null && teacher.getAccount() != null
					&& !StringUtil.isNullOrEmpty(teacher.getAccount())) {
				crit.add(Restrictions.like("account", teacher.getAccount()));
			}
			if (teacher != null && teacher.getName() != null
					&& !StringUtil.isNullOrEmpty(teacher.getName())) {
				crit.add(Restrictions.like("name", teacher.getName()));
			}
			teachers = crit.list();
			if (teachers != null && !teachers.isEmpty()) {
				total = teachers.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> getTeachersByDepartment(Integer id) {
		List<Teacher> teachers = new ArrayList<Teacher>();
		Session session = super.getSession();
		try {
			Criteria crit = session.createCriteria(Teacher.class);
			if (id != null) {
				crit.add(Restrictions.eq("department.id", id));
			}
			teachers = crit.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teachers;
	}

}
