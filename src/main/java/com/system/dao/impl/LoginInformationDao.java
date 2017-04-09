package com.system.dao.impl;

import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.system.bean.LoginInformatin4Bean;
import com.system.dao.ILoginInformationDao;
import com.system.entity.LoginInformation;

@Repository
public class LoginInformationDao extends BaseDao<LoginInformation, Integer>
		implements ILoginInformationDao {

	@SuppressWarnings("unchecked")
	public LoginInformation checkLoginInformation(LoginInformatin4Bean bean) {
		LoginInformation loginInformation = null;
		Session session = super.getSession();
		String hql = "from LoginInformation L where L.account = ? and L.password = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, bean.getAccount());
		query.setParameter(1, bean.getPassword());
		List<LoginInformation> list = (List<LoginInformation>) query.list();
		if (!list.isEmpty()) {
			loginInformation = list.get(0);
		}
		return loginInformation;
	}

	@Override
	public void deleteByAccount(String account) {
		Session session = super.getSession();
		String hql = "delete LoginInformation L where L.account = :account";
		Query query = session.createQuery(hql);
		query.setParameter("account", account);
		query.executeUpdate();
	}
}
