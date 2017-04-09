package com.system.dao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T extends Serializable, IdType extends Serializable> {

	List<T> findAll();

	T findById(IdType id);

	List<T> findListById(IdType id);

	T findByAccount(String account);

	List<T> getPageList(Integer page, Integer rows);

	Integer getPageTotal();

	boolean save(T entity);

	boolean deleteById(IdType id);

	boolean modify(T entity);

	T findByName(String name);
	
	boolean remove(T entity);
}
