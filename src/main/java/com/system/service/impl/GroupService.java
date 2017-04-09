package com.system.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.dao.IGroupDao;
import com.system.entity.Group;
import com.system.entity.Student;
import com.system.service.IGroupService;

@Service
@Transactional
public class GroupService extends BaseService<Group, Integer> implements
		IGroupService {

	@Resource
	private IGroupDao dao;

	@PostConstruct
	public void init() {
		super.setDao(dao);
	}

	@Override
	public List<Group> getGroups(Integer departmentId, Group group,
			Integer page, Integer rows) {
		return dao.getGroups(departmentId, group, page, rows);
	}

	@Override
	public Integer getGroupTotal(Integer departmentId, Group group) {
		return dao.getGroupTotal(departmentId, group);
	}

	@Override
	public List<Student> getGroupStudents(Integer departmentId, Integer grade,
			Integer teacherId) {
		return dao.getGroupStudents(departmentId, grade, teacherId);
	}

}
