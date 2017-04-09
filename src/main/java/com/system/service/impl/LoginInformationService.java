package com.system.service.impl;

import javax.annotation.PostConstruct;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.bean.LoginInformatin4Bean;
import com.system.dao.ILoginInformationDao;
import com.system.entity.LoginInformation;
import com.system.service.ILoginInformationService;

@Service
@Transactional
public class LoginInformationService extends
		BaseService<LoginInformation, Integer> implements
		ILoginInformationService {

	@Resource
	private ILoginInformationDao dao;

	@PostConstruct
	public void init() {
		super.setDao(dao);
	}

	public LoginInformation checkLoginInformation(LoginInformatin4Bean bean) {
		return dao.checkLoginInformation(bean);
	}

	@Override
	public void deleteByAccount(String account) {
		dao.deleteByAccount(account);
	}

}
