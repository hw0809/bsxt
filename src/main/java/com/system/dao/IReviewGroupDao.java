package com.system.dao;

import java.util.List;

import com.system.entity.ReviewTeacherGroup;
import com.system.entity.Student;

public interface IReviewGroupDao extends IBaseDao<ReviewTeacherGroup, Integer> {

	List<ReviewTeacherGroup> getGroups(Integer departmentId,
			ReviewTeacherGroup group, Integer page, Integer rows);

	Integer getGroupTotal(Integer departmentId, ReviewTeacherGroup group);

	List<Student> getReviewStudents(Integer departmentId, Integer grade,
			Integer teacherId);
}
