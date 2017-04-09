package com.system.dao.impl;

import java.io.Serializable;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.system.dao.IBaseDao;
import com.system.entity.impl.IEntity;

@SuppressWarnings("unchecked")
public class BaseDao<T extends IEntity<IdType>, IdType extends Serializable>
		implements IBaseDao<T, IdType> {

	private String classSimpleName;

	private String className;

	public BaseDao() {
		ParameterizedType pt = (ParameterizedType) getClass()
				.getGenericSuperclass();
		Type[] types = pt.getActualTypeArguments();
		clazz = (Class<T>) types[0];
		classSimpleName = clazz.getSimpleName();
		className = clazz.getName();
	}

	private Class<T> clazz;

	@Resource
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<T> findAll() {
		Session session = this.getSession();
		List<T> list = null;
		try {
			String hql = "from " + classSimpleName;
			Query query = session.createQuery(hql);
			list = query.list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public T findById(IdType id) {
		Session session = this.getSession();
		T entity = null;
		try {
			entity = (T) session.get(className, id);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public List<T> getPageList(Integer page, Integer rows) {
		Session session = this.getSession();
		List<T> list = null;
		try {
			Criteria crit = session.createCriteria(className);
			crit.addOrder(Order.desc("id"));
			crit.setFirstResult((page - 1) * rows);
			crit.setMaxResults(rows);
			list = crit.list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Integer getPageTotal() {
		Session session = this.getSession();
		Number total = null;
		try {
			Criteria crit = session.createCriteria(className);
			crit.setProjection(Projections.rowCount());
			total = (Number) crit.uniqueResult();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return total.intValue();
	}

	@Override
	public T findByAccount(String account) {
		T pojo = null;
		Session session = this.getSession();
		try {
			Criteria crit = session.createCriteria(className);
			List<T> list = crit.add(Restrictions.eq("account", account)).list();
			if (!list.isEmpty()) {
				pojo = list.get(0);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return pojo;
	}

	@Override
	public boolean save(T entity) {
		boolean flag = false;
		Session session = this.getSession();
		try {
			session.save(entity);
			flag = true;
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<T> findListById(IdType id) {
		Session session = this.getSession();
		List<T> list = null;
		try {
			Criteria crit = session.createCriteria(className);
			list = crit.add(Restrictions.eq("id", id)).list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean deleteById(IdType id) {
		boolean flag = false;
		try {
			Session session = this.getSession();
			T entity = (T) session.get(className, id);
			session.delete(entity);
			session.flush();
			flag = true;
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean modify(T entity) {
		boolean flag = false;
		try {
			Session session = this.getSession();
			session.update(entity);
			session.flush();
			flag = true;
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public T findByName(String name) {
		T pojo = null;
		Session session = this.getSession();
		try {
			Criteria crit = session.createCriteria(className);
			List<T> list = crit.add(Restrictions.eq("name", name)).list();
			if (!list.isEmpty()) {
				pojo = list.get(0);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return pojo;
	}

	@Override
	public boolean remove(T entity) {
		boolean flag = false;
		Session session = this.getSession();
		/*try {
			session.delete(entity);
			session.
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return flag;
	}
}
