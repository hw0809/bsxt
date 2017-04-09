package com.system.service.impl;

import javax.annotation.PostConstruct;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.dao.IAdminstratorDao;
import com.system.entity.Adminstrator;
import com.system.service.IAdminstratorService;

@Service
@Transactional
public class AdminstratorService extends BaseService<Adminstrator, Integer>
		implements IAdminstratorService {

	@Resource
	private IAdminstratorDao dao;

	@PostConstruct
	public void init() {
		super.setDao(dao);
	}

}
