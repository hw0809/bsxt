package com.system.dao.impl;

import java.util.ArrayList;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.system.dao.IReviewGroupDao;
import com.system.entity.ReviewTeacherGroup;
import com.system.entity.Student;

@Repository
public class ReviewGroupDao extends BaseDao<ReviewTeacherGroup, Integer>
		implements IReviewGroupDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ReviewTeacherGroup> getGroups(Integer departmentId,
			ReviewTeacherGroup group, Integer page, Integer rows) {
		List<ReviewTeacherGroup> groups = new ArrayList<ReviewTeacherGroup>();
		Session session = super.getSession();
		Criteria crit = session.createCriteria(ReviewTeacherGroup.class);
		crit.add(Restrictions.eq("department.id", departmentId));
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
	public Integer getGroupTotal(Integer departmentId, ReviewTeacherGroup group) {
		Integer total = 0;
		List<ReviewTeacherGroup> groups = new ArrayList<ReviewTeacherGroup>();
		Session session = super.getSession();
		Criteria crit = session.createCriteria(ReviewTeacherGroup.class);
		crit.add(Restrictions.eq("department.id", departmentId));
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
	public List<Student> getReviewStudents(Integer departmentId, Integer grade,
			Integer teacherId) {
		Session session = super.getSession();
		List<ReviewTeacherGroup> list = new ArrayList<ReviewTeacherGroup>();

		Criteria crit = session.createCriteria(ReviewTeacherGroup.class);
		crit.add(Restrictions.eq("grade", grade));
		crit.add(Restrictions.eq("department.id", departmentId));
		crit.createAlias("teachers", "teachers").add(
				Restrictions.eq("teachers.id", teacherId));

		list = crit.list();
		
		List<Student> students = null;
		for (ReviewTeacherGroup group : list) {
			students = group.getStudents();
		}
		
		return students;
	}
}
