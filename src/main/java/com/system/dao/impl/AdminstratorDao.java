package com.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.system.dao.IAdminstratorDao;
import com.system.entity.Adminstrator;

@Repository
public class AdminstratorDao extends BaseDao<Adminstrator, Integer> implements
		IAdminstratorDao {

}
