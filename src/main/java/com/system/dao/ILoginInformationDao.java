package com.system.dao;

import com.system.bean.LoginInformatin4Bean;
import com.system.entity.LoginInformation;

public interface ILoginInformationDao extends
		IBaseDao<LoginInformation, Integer> {

	public LoginInformation checkLoginInformation(LoginInformatin4Bean bean);
	
	public void deleteByAccount(String account);
}
