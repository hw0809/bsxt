package com.system.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.dao.IReviewGroupDao;
import com.system.entity.ReviewTeacherGroup;
import com.system.entity.Student;
import com.system.service.IReviewGroupService;

@Service
@Transactional
public class ReviewGroupService extends
		BaseService<ReviewTeacherGroup, Integer> implements IReviewGroupService {
	@Resource
	private IReviewGroupDao dao;

	@PostConstruct
	public void init() {
		super.setDao(dao);
	}

	@Override
	public List<ReviewTeacherGroup> getGroups(Integer departmentId,
			ReviewTeacherGroup group, Integer page, Integer rows) {
		return dao.getGroups(departmentId, group, page, rows);
	}

	@Override
	public Integer getGroupTotal(Integer departmentId, ReviewTeacherGroup group) {
		return dao.getGroupTotal(departmentId, group);
	}

	@Override
	public List<Student> getReviewStudents(Integer departmentId, Integer grade,
			Integer teacherId) {
		return dao.getReviewStudents(departmentId, grade, teacherId);
	}

}
