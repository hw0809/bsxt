package com.system.dao.impl;

import java.util.ArrayList;

import java.util.Set;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.system.dao.IPaperDao;
import com.system.entity.Classes;
import com.system.entity.Paper;
import com.system.entity.Teacher;
import com.system.entity4Json.Paper4Json;
import com.system.util.StringUtil;

@Repository
public class PaperDao extends BaseDao<Paper, Integer> implements IPaperDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Paper4Json> getPaperPageList(Integer teacherId, Paper paper,
			Integer page, Integer rows) {
		Session session = super.getSession();
		List<Paper> papers = null;
		List<Paper4Json> list = new ArrayList<Paper4Json>();
		try {
			Criteria crit = session.createCriteria(Paper.class);
			if (paper.getTitle() != null) {
				crit.add(Restrictions.like("title", paper.getTitle()));
			}
			// if (paper.getTitleReview() != null) {
			// 3 是全部
			if (paper.getTitleReview() != 3) {
				crit.add(Restrictions.eq("titleReview", paper.getTitleReview()));
			}
			// }

			crit.add(Restrictions.eq("teacher.id", teacherId));

			if (paper.getGrade() != null) {
				crit.add(Restrictions.eqOrIsNull("grade", paper.getGrade()));
			}
			crit.setFirstResult((page - 1) * rows);
			crit.setMaxResults(rows);
			papers = crit.list();
			if (!papers.isEmpty()) {
				Paper4Json json = null;
				for (Paper item : papers) {
					json = new Paper4Json();
					json.setId(item.getId());
					json.setGrade(item.getGrade());
					json.setTitle(item.getTitle());
					json.setTitleReview(item.getTitleReview());
					json.setNum(item.getNum());

					Set<Classes> cSet = item.getClasses();
					String str = "";
					for (Classes classes : cSet) {
						str += classes.getName() + ",";
					}
					str = str.substring(0, str.length() - 1);
					json.setClassName(str);
					//
					// List<Classes> classes = item.getClasses();
					/*
					 * for (Classes c : classes) { }
					 * 
					 * json.setClassName(str);
					 */
					list.add(json);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paper4Json> getPaperPageList(Paper paper, Integer page,
			Integer rows, Integer teacherId) {
		Session session = super.getSession();
		List<Paper> papers = null;
		List<Paper4Json> list = new ArrayList<Paper4Json>();
		try {
			Criteria crit = session.createCriteria(Paper.class);
			if (paper.getTitle() != null) {
				crit.add(Restrictions.like("title", paper.getTitle()));
			}
			// if (paper.getTitleReview() != null) {
			// 3 是全部
			if (paper.getTitleReview() != 3) {
				crit.add(Restrictions.eq("titleReview", paper.getTitleReview()));
			}
			// }

			if (paper.getGrade() != null) {
				crit.add(Restrictions.eqOrIsNull("grade", paper.getGrade()));
			}
			crit.add(Restrictions.eq("teacher.id", teacherId));
			crit.setFirstResult((page - 1) * rows);
			crit.setMaxResults(rows);
			papers = crit.list();
			if (!papers.isEmpty()) {
				Paper4Json json = null;
				for (Paper item : papers) {
					json = new Paper4Json();
					json.setId(item.getId());
					json.setGrade(item.getGrade());
					json.setTitle(item.getTitle());
					json.setTitleReview(item.getTitleReview());
					json.setNum(item.getNum());

					Set<Classes> cSet = item.getClasses();
					String str = "";
					for (Classes classes : cSet) {
						str += classes.getName() + ",";
					}
					str = str.substring(0, str.length() - 1);
					json.setClassName(str);
					//
					// List<Classes> classes = item.getClasses();
					/*
					 * for (Classes c : classes) { }
					 * 
					 * json.setClassName(str);
					 */
					list.add(json);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Paper> getTopics(Integer classesId) {
		Session session = super.getSession();
		List<Paper> papers = null;
		String sql = "select * from t_paper_classes where f_classesid = ?";
		@SuppressWarnings("unchecked")
		List<Object> list = session.createSQLQuery(sql)
				.setParameter(0, classesId).list();
		for (Object object : list) {
			Object[] ids = (Object[]) object;
			System.out.println(ids[0].toString());
			System.out.println(44);
		}

		return papers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getPaperTotal(Paper paper) {
		Session session = super.getSession();
		List<Paper> papers = null;
		Integer total = 0;
		try {
			Criteria crit = session.createCriteria(Paper.class);
			if (paper.getTitle() != null) {
				crit.add(Restrictions.like("title", paper.getTitle()));
			}
			if (paper.getTitleReview() != null) {
				crit.add(Restrictions.eq("titleReview", paper.getTitleReview()));
			}
			if (paper.getGrade() != null) {
				crit.add(Restrictions.eqOrIsNull("grade", paper.getGrade()));
			}
			papers = crit.list();
			if (!papers.isEmpty()) {
				total = papers.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public boolean saveRelation(Integer paperId, Integer classesId) {
		boolean flag = false;
		Session session = null;
		session = super.getSession();
		String sql = "insert into t_paper_classes(f_classesid, f_paperid) values (?,?)";
		Query query = session.createSQLQuery(sql);
		query.setInteger(0, classesId);
		query.setInteger(1, paperId);
		query.executeUpdate();
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkPaper(String title, Integer grade, Integer teacherId) {
		boolean flag = false;
		Session session = super.getSession();
		List<Paper> papers = null;
		try {
			Criteria crit = session.createCriteria(Paper.class);
			if (title != null) {
				crit.add(Restrictions.eq("title", title));
			}
			if (grade != null) {
				crit.add(Restrictions.eq("grade", grade));
			}
			if (teacherId != null) {
				crit.add(Restrictions.eq("teacher.id", teacherId));
			}
			papers = crit.list();
			if (papers != null && papers.size() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paper4Json> getReviewPageList(Paper paper, Integer page,
			Integer rows, Integer departmentId) {
		Session session = super.getSession();
		List<Teacher> teachers = null;
		try {
			String hql = "from Teacher t where t.department.id = '"
					+ departmentId + "'";
			Query query = session.createQuery(hql);
			teachers = query.list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		List<Integer> ids = new ArrayList<Integer>();
		for (Teacher item : teachers) {
			ids.add(item.getId());
		}
		Integer[] teacherIds = (Integer[]) ids.toArray(new Integer[teachers
				.size()]);

		List<Paper> papers = null;
		List<Paper4Json> list = new ArrayList<Paper4Json>();
		try {
			Criteria crit = session.createCriteria(Paper.class);
			if (paper.getTitle() != null) {
				crit.add(Restrictions.like("title", paper.getTitle()));
			}
			if (paper.getTitleReview() != null) {
				if (paper.getTitleReview() != 3) {
					// 3是全部
					crit.add(Restrictions.eq("titleReview",
							paper.getTitleReview()));
				}
			}
			if (paper.getGrade() != null) {
				crit.add(Restrictions.eqOrIsNull("grade", paper.getGrade()));
			}
			Integer id = 0;
			if (paper.getClasses() != null && !paper.getClasses().isEmpty()) {
				Set<Classes> classes = paper.getClasses();
				for (Classes c : classes) {
					id = c.getId();
				}

				String pSql = "select f_paperid from t_paper_classes where f_classesid = ?";
				List<Integer> idsList = session.createSQLQuery(pSql)
						.setParameter(0, id).list();
				List<Integer> classesIds = new ArrayList<Integer>();
				if (idsList.isEmpty()) {
					classesIds.add(0);
				}
				for (Integer object : idsList) {
					classesIds.add(object);
				}
				if (!classesIds.isEmpty()) {
					crit.add(Restrictions.in("id", classesIds));
				}
			}

			crit.add(Restrictions.in("teacher.id", teacherIds));
			crit.setFirstResult((page - 1) * rows);
			crit.setMaxResults(rows);
			papers = crit.list();
			if (!papers.isEmpty()) {
				Paper4Json json = null;
				for (Paper item : papers) {
					json = new Paper4Json();
					json.setId(item.getId());
					json.setGrade(item.getGrade());
					json.setTitle(item.getTitle());
					json.setTitleReview(item.getTitleReview());
					json.setNum(item.getNum());

					String sql = "select f_classesid from t_paper_classes where f_paperid = ?";
					List<Integer> classesList = session.createSQLQuery(sql)
							.setParameter(0, item.getId()).list();
					String classesName = "";
					for (Integer object : classesList) {
						String hql = "from Classes c where c.id = '" + object
								+ "'";
						Query query = session.createQuery(hql);
						List<Classes> cLists = query.list();
						if (!cLists.isEmpty()) {
							classesName += cLists.get(0).getName() + ";";
						}
					}
					classesName = classesName.substring(0,
							classesName.length() - 1);
					json.setClassName(classesName);

					list.add(json);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getReviewPaperTotal(Paper paper, Integer departmentId) {
		Integer total = 0;

		Session session = super.getSession();
		List<Teacher> teachers = null;
		try {
			String hql = "from Teacher t where t.department.id = '"
					+ departmentId + "'";
			Query query = session.createQuery(hql);
			teachers = query.list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		List<Integer> ids = new ArrayList<Integer>();
		for (Teacher item : teachers) {
			ids.add(item.getId());
		}
		Integer[] teacherIds = (Integer[]) ids.toArray(new Integer[teachers
				.size()]);

		List<Paper> papers = null;
		try {
			Criteria crit = session.createCriteria(Paper.class);
			if (paper.getTitle() != null) {
				crit.add(Restrictions.like("title", paper.getTitle()));
			}
			if (paper.getTitleReview() != null) {
				if (paper.getTitleReview() != 3) {
					// 3是全部
					crit.add(Restrictions.eq("titleReview",
							paper.getTitleReview()));
				}
			}
			if (paper.getGrade() != null) {
				crit.add(Restrictions.eqOrIsNull("grade", paper.getGrade()));
			}
			crit.add(Restrictions.in("teacher.id", teacherIds));
			papers = crit.list();
			if (!papers.isEmpty()) {
				total = papers.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paper> getAllPaper(Paper paper, Integer page, Integer rows,
			Integer classesId) {
		Session session = super.getSession();
		List<Paper> list = new ArrayList<Paper>();
		try {
			String sql = "select f_paperid from t_paper_classes where f_classesid = ?";
			List<Integer> papers = session.createSQLQuery(sql)
					.setParameter(0, classesId).list();

			Criteria crit = session.createCriteria(Paper.class);
			crit.add(Restrictions.eq("titleReview", 1));
			crit.add(Restrictions.in("id", papers));
			if (paper != null && !StringUtil.isNullOrEmpty(paper.getTitle())) {
				crit.add(Restrictions.like("title", paper.getTitle()));
			}
			crit.setFirstResult((page - 1) * rows);
			crit.setMaxResults(rows);
			list = crit.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getAllPaperTotal(Paper paper, Integer classesId) {
		Session session = super.getSession();
		List<Paper> list = new ArrayList<Paper>();
		Integer total = 0;
		try {
			String sql = "select f_classesid from t_paper_classes where f_classesid = ?";

			List<Integer> papers = session.createSQLQuery(sql)
					.setParameter(0, classesId).list();

			Criteria crit = session.createCriteria(Paper.class);
			crit.add(Restrictions.eq("titleReview", 1));
			crit.add(Restrictions.in("id", papers));
			if (paper != null && !StringUtil.isNullOrEmpty(paper.getTitle())) {
				crit.add(Restrictions.like("title", paper.getTitle()));
			}
			list = crit.list();
			if (list != null && !list.isEmpty()) {
				total = list.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

}
