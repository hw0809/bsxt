package com.system.service;

import com.system.entity.Department;

public interface IDepartmentService extends IBaseService<Department, Integer> {
	Department getDepartmentByCode(Integer departmenCode);
}
