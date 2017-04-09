package com.system.dao;

import com.system.entity.Department;

public interface IDepartmentDao extends IBaseDao<Department, Integer> {

	Department getDepartmentByCode(Integer departmenCode);

}
