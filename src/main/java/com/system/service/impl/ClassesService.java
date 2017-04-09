package com.system.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.dao.IClassesDao;
import com.system.entity.Classes;
import com.system.service.IClassesService;

@Service
@Transactional
public class ClassesService extends BaseService<Classes, Integer> implements
		IClassesService {

	@Resource
	private IClassesDao dao;

	@PostConstruct
	public void init() {
		super.setDao(dao);
	}

	@Override
	public List<Classes> getDepartmentById(Integer id) {
		return dao.getDepartmentById(id);
	}

	@Override
	public List<Classes> getClassesByCode(Integer code) {
		return dao.getClassesByCode(code);
	}

	@Override
	public Integer getClassesId(String classesName, Integer grade) {
		return dao.getClassesId(classesName, grade);
	}

	@Override
	public List<Classes> getPageList(Classes classes, Integer page,
			Integer rows, Integer departmentId) {
		return dao.getPageList(classes, page, rows, departmentId);
	}

	@Override
	public Integer getPageTotal(Classes classes, Integer departmentId) {
		return dao.getPageTotal(classes, departmentId);
	}

	@Override
	public Classes getClasses(Integer grade, String name) {
		return dao.getClasses(grade, name);
	}

	@Override
	public List<Classes> getClassesByDepartment(Integer id) {
		return dao.getClassesByDepartment(id);
	}

}
