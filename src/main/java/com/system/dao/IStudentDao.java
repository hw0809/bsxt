package com.system.dao;

import java.util.List;


import com.system.entity.Student;

public interface IStudentDao extends IBaseDao<Student, Integer> {
	Student findByAccount(String account);

	List<Student> getPageList(Student student, Integer page, Integer rows,
			Integer departmentId);

	Integer getPageTotal(Student student, Integer departmentId);
	
	List<Student> getStudentsByDepartment(Integer id);
	
	List<Student> getStudentsByClassesId(Integer classesId);

	// List<Paper> getTopics(Student student);
}
