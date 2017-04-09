package com.system.service;

import java.util.List;

import com.system.entity.Teacher;

public interface ITeacherService extends IBaseService<Teacher, Integer> {

	List<Teacher> getPageList(Teacher teacher, Integer page,
			Integer rows, Integer departmentId);
	
	Integer getPageTotal(Teacher teacher, Integer departmentId);
	
	List<Teacher> getTeachersByDepartment(Integer code);
}
