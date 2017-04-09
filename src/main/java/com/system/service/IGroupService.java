package com.system.service;

import java.util.List;

import com.system.entity.Group;
import com.system.entity.Student;

public interface IGroupService extends IBaseService<Group, Integer> {

	List<Group> getGroups(Integer departmentId, Group group, Integer page,
			Integer rows);

	Integer getGroupTotal(Integer departmentId, Group group);

	List<Student> getGroupStudents(Integer departmentId, Integer grade,
			Integer teacherId);
	
}
