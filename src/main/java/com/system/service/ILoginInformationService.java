package com.system.service;

import com.system.bean.LoginInformatin4Bean;

import com.system.entity.LoginInformation;

public interface ILoginInformationService extends
		IBaseService<LoginInformation, Integer> {

	public LoginInformation checkLoginInformation(LoginInformatin4Bean bean);

	public void deleteByAccount(String account);
}
