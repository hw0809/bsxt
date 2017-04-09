package com.system.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.system.dao.IDepartmentDao;
import com.system.entity.Department;

@Repository
public class DepartmentDao extends BaseDao<Department, Integer> implements
		IDepartmentDao {

	@Override
	public Department getDepartmentByCode(Integer departmenCode) {
		Department department = new Department();
		Session session = super.getSession();
		Criteria crit = session.createCriteria(Department.class);
		@SuppressWarnings("unchecked")
		List<Department> list = crit.add(
				Restrictions.eq("departmentCode", departmenCode)).list();
		if (!list.isEmpty()) {
			department = list.get(0);
		}
		return department;
	}

}
