package com.system.service.impl;

import javax.annotation.PostConstruct;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.dao.IDepartmentDao;
import com.system.entity.Department;
import com.system.service.IDepartmentService;

@Service
@Transactional
public class DepartmentServcice extends BaseService<Department, Integer>
		implements IDepartmentService {

	@Resource
	private IDepartmentDao dao;

	@PostConstruct
	public void init() {
		super.setDao(dao);
	}

	public Department getDepartmentByCode(Integer departmenCode) {
		return dao.getDepartmentByCode(departmenCode);
	}
}
