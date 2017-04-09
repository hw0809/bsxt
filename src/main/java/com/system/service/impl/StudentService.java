package com.system.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.dao.IStudentDao;
import com.system.entity.Student;
import com.system.service.IStudentService;

@Service
@Transactional
public class StudentService extends BaseService<Student, Integer> implements
		IStudentService {

	@Resource
	private IStudentDao dao;

	@PostConstruct
	public void init() {
		super.setDao(dao);
	}

	@Override
	public List<Student> getPageList(Student student, Integer page,
			Integer rows, Integer departmentId) {
		return dao.getPageList(student, page, rows, departmentId);
	}

	@Override
	public Integer getPageTotal(Student student, Integer departmentId) {
		return dao.getPageTotal(student, departmentId);
	}

	@Override
	public List<Student> getStudentsByDepartment(Integer id) {
		return dao.getStudentsByDepartment(id);
	}

	@Override
	public List<Student> getStudentsByClassesId(Integer classesId) {
		return dao.getStudentsByClassesId(classesId);
	}

}
