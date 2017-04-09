package com.system.dao.impl;

import java.util.ArrayList;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.system.dao.IClassesDao;
import com.system.entity.Classes;
import com.system.util.StringUtil;

@Repository
public class ClassesDao extends BaseDao<Classes, Integer> implements
		IClassesDao {

	@Override
	public List<Classes> getDepartmentById(Integer id) {
		Session session = super.getSession();
		String hql = "from Classes as c where c.department.id = :id";// 使用命名参数，推荐使用，易读。
		Query query = session.createQuery(hql);
		query.setInteger("id", id);
		@SuppressWarnings("unchecked")
		List<Classes> list = query.list();
		return list;
	}

	@Override
	public List<Classes> getClassesByCode(Integer code) {
		Session session = super.getSession();
		String hql = "from Classes as c where c.department.departmentCode = :code";
		Query query = session.createQuery(hql);
		query.setInteger("code", code);
		@SuppressWarnings("unchecked")
		List<Classes> list = query.list();
		return list;
	}

	@Override
	public Integer getClassesId(String classesName, Integer grade) {
		Integer id = 0;
		Session session = super.getSession();
		String hql = "from Classes as c where c.grade = :grade and c.name = :name";
		Query query = session.createQuery(hql);
		query.setInteger("grade", grade);
		query.setString("name", classesName);
		@SuppressWarnings("unchecked")
		List<Classes> list = query.list();
		if (!list.isEmpty()) {
			id = list.get(0).getId();
		}
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Classes> getPageList(Classes classes, Integer page,
			Integer rows, Integer departmentId) {
		Session session = super.getSession();
		List<Classes> list = new ArrayList<Classes>();
		try {
			Criteria crit = session.createCriteria(Classes.class);
			if (classes != null && classes.getName() != null
					&& !StringUtil.isNullOrEmpty(classes.getName())) {
				crit.add(Restrictions.like("name", classes.getName()));
			}
			crit.add(Restrictions.eq("department.id", departmentId));
			crit.setFirstResult((page - 1) * rows);
			crit.setMaxResults(rows);
			list = crit.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getPageTotal(Classes classes, Integer departmentId) {
		Integer total = 0;
		Session session = super.getSession();
		List<Classes> list = new ArrayList<Classes>();
		try {
			Criteria crit = session.createCriteria(Classes.class);
			if (classes != null && classes.getName() != null
					&& !StringUtil.isNullOrEmpty(classes.getName())) {
				crit.add(Restrictions.like("name", classes.getName()));
			}
			list = crit.list();
			if (list != null && !list.isEmpty()) {
				total = list.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public Classes getClasses(Integer grade, String name) {
		Session session = super.getSession();
		String hql = "from Classes as c where c.grade = :grade and c.name = :name";// 使用命名参数，推荐使用，易读。
		Query query = session.createQuery(hql);
		query.setInteger("grade", grade);
		query.setString("name", name);
		@SuppressWarnings("unchecked")
		List<Classes> list = query.list();
		Classes classes = null;
		if (list != null) {
			classes = list.get(0);
		}
		return classes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Classes> getClassesByDepartment(Integer id) {
		List<Classes> classes = new ArrayList<Classes>();
		Session session = super.getSession();
		try {
			Criteria crit = session.createCriteria(Classes.class);
			if (id != null) {
				crit.add(Restrictions.eq("department.id", id));
			}
			classes = crit.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classes;
	}

}
