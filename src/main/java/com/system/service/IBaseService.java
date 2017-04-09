package com.system.service;

import java.io.Serializable;
import java.util.List;

import com.system.entity.impl.IEntity;

public interface IBaseService<T extends IEntity<IdType>, IdType extends Serializable> {

	List<T> findAll();

	T findById(IdType id);

	T findByAccount(String account);

	List<T> getPageList(Integer page, Integer rows);

	Integer getPageTotal();

	boolean save(T entity);

	List<T> findListById(IdType id);

	boolean deleteById(IdType id);

	boolean modify(T entity);

	T findByName(String name);
	
	boolean remove(T entity);
}
