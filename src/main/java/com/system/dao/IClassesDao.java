package com.system.dao;

import java.util.List;

import com.system.entity.Classes;

public interface IClassesDao extends IBaseDao<Classes, Integer> {

	List<Classes> getDepartmentById(Integer id);

	List<Classes> getClassesByCode(Integer code);

	Integer getClassesId(String classesName, Integer grade);

	List<Classes> getPageList(Classes classes, Integer page, Integer rows,
			Integer departmentId);

	Integer getPageTotal(Classes classes, Integer departmentId);

	Classes getClasses(Integer grade, String name);

	List<Classes> getClassesByDepartment(Integer id);
}
