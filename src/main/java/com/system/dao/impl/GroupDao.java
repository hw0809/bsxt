package com.system.dao.impl;

import java.util.ArrayList;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.system.dao.IGroupDao;
import com.system.entity.Group;
import com.system.entity.Student;

@Repository
public class GroupDao extends BaseDao<Group, Integer> implements IGroupDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getGroups(Integer departmentId, Group group,
			Integer page, Integer rows) {
		List<Group> groups = new ArrayList<Group>();
		Session session = super.getSession();
		Criteria crit = session.createCriteria(Group.class);
		crit.add(Restrictions.eq("department.id", departmentId));
		if (group.getName() != null && !group.getName().equals("")) {
			crit.add(Restrictions.like("name", group.getName()));
		}
		if (group.getGrade() != null) {
			crit.add(Restrictions.eq("grade", group.getGrade()));
		}
		crit.setFirstResult((page - 1) * rows);
		crit.setMaxResults(rows);
		groups = crit.list();
		return groups;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getGroupTotal(Integer departmentId, Group group) {
		Integer total = 0;
		List<Group> groups = new ArrayList<Group>();
		Session session = super.getSession();
		Criteria crit = session.createCriteria(Group.class);
		crit.add(Restrictions.eq("department.id", departmentId));
		if (group.getName() != null && !group.getName().equals("")) {
			crit.add(Restrictions.like("name", group.getName()));
		}
		if (group.getGrade() != null) {
			crit.add(Restrictions.eq("grade", group.getGrade()));
		}
		groups = crit.list();
		if (groups != null) {
			total = groups.size();
		}
		return total;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getGroupStudents(Integer departmentId, Integer grade,
			Integer teacherId) {
		Session session = super.getSession();
		List<Group> list = new ArrayList<Group>();

		Criteria crit = session.createCriteria(Group.class);
		crit.add(Restrictions.eq("grade", grade));
		crit.add(Restrictions.eq("department.id", departmentId));
		crit.createAlias("teachers", "teachers").add(
				Restrictions.eq("teachers.id", teacherId));

		list = crit.list();

		List<Student> students = null;
		for (Group group : list) {
			students = group.getStudents();
		}

		return students;
	}

}
