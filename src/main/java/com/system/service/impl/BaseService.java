package com.system.service.impl;

import java.io.Serializable;

import java.util.List;

import com.system.dao.IBaseDao;
import com.system.entity.impl.IEntity;
import com.system.service.IBaseService;

public class BaseService<T extends IEntity<IdType>, IdType extends Serializable>
		implements IBaseService<T, IdType> {

	private IBaseDao<T, IdType> dao;

	public void setDao(IBaseDao<T, IdType> dao) {
		this.dao = dao;
	}

	@Override
	public List<T> findAll() {
		return dao.findAll();
	}

	@Override
	public T findById(IdType id) {
		return dao.findById(id);
	}

	@Override
	public List<T> getPageList(Integer page, Integer rows) {
		return dao.getPageList(page, rows);
	}

	@Override
	public Integer getPageTotal() {
		return dao.getPageTotal();
	}

	@Override
	public T findByAccount(String account) {
		return dao.findByAccount(account);
	}

	@Override
	public boolean save(T entity) {
		return dao.save(entity);
	}

	@Override
	public List<T> findListById(IdType id) {
		return dao.findListById(id);
	}

	@Override
	public boolean deleteById(IdType id) {
		return dao.deleteById(id);
	}

	@Override
	public boolean modify(T entity) {
		return dao.modify(entity);
	}

	@Override
	public T findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public boolean remove(T entity) {
		return dao.remove(entity);
	}

}
