package com.system.dao.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.system.entity.Hourse;
import com.system.entity.Person;

@Repository
public class TestDao {

	@Resource
	private SessionFactory sessionFactory;

	public void test() {
		/*
		 * Person person = new Person(); person.setName("cc");
		 * session.save(person);
		 */
		/*
		 * Hourse hourse = new Hourse(); hourse.setName("dd");
		 * session.save(hourse);
		 */

		Session session = null;
		session = sessionFactory.getCurrentSession();

		Person person = new Person();
		person.setName("cy");

		Hourse h1 = new Hourse();
		Hourse h2 = new Hourse();
		Hourse h3 = new Hourse();

		h1.setName("hhh1");
		h2.setName("hhh2");
		h3.setName("hhh3");
		Set<Hourse> hourses = new HashSet<Hourse>();
		hourses.add(h1);
		hourses.add(h2);
		hourses.add(h3);

		person.setHourses(hourses);

		session.save(person);
	}
}
