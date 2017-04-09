package com.system.service;

import java.util.List;

import com.system.entity.Student;

public interface IStudentService extends IBaseService<Student, Integer> {
	
	List<Student> getPageList(Student student, Integer page, Integer rows,
			Integer departmentId);

	Integer getPageTotal(Student student, Integer departmentId);
	
	List<Student> getStudentsByDepartment(Integer id);
	
	List<Student> getStudentsByClassesId(Integer classesId);
}
