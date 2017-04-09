package com.system.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.dao.ITeacherDao;
import com.system.entity.Teacher;
import com.system.service.ITeacherService;

@Service
@Transactional
public class TeacherService extends BaseService<Teacher, Integer> implements
		ITeacherService {

	@Resource
	private ITeacherDao dao;

	@PostConstruct
	public void init() {
		super.setDao(dao);
	}

	@Override
	public List<Teacher> getPageList(Teacher teacher, Integer page,
			Integer rows, Integer departmentId) {
		return dao.getPageList(teacher, page, rows, departmentId);
	}

	@Override
	public Integer getPageTotal(Teacher teacher, Integer departmentId) {
		return dao.getPageTotal(teacher, departmentId);
	}

	@Override
	public List<Teacher> getTeachersByDepartment(Integer code) {
		return dao.getTeachersByDepartment(code);
	}

}
