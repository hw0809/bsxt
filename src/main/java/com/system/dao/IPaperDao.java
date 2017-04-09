package com.system.dao;

import java.util.List;

import com.system.entity.Paper;
import com.system.entity4Json.Paper4Json;

public interface IPaperDao extends IBaseDao<Paper, Integer> {

	List<Paper4Json> getPaperPageList(Paper paper, Integer page, Integer rows,  Integer teacherId);

	List<Paper4Json> getPaperPageList(Integer teacherId, Paper paper,
			Integer page, Integer rows);

	List<Paper4Json> getReviewPageList(Paper paper, Integer page, Integer rows,
			Integer departmentId);

	Integer getReviewPaperTotal(Paper paper, Integer departmentId);

	Integer getPaperTotal(Paper paper);

	List<Paper> getTopics(Integer classesId);

	boolean saveRelation(Integer paperId, Integer classesId);

	// 是否存在同年同个老师同个题目
	boolean checkPaper(String title, Integer grade, Integer teacherId);

	List<Paper> getAllPaper(Paper paper, Integer page, Integer rows,
			Integer classesId);

	Integer getAllPaperTotal(Paper paper, Integer classesId);
}
