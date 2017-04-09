package com.system.service;

import java.util.List;

import com.system.entity.Paper;
import com.system.entity4Json.Paper4Json;

public interface IPaperService extends IBaseService<Paper, Integer> {

	List<Paper4Json> getPaperPageList(Integer teacherId, Paper paper,
			Integer page, Integer rows);

	List<Paper4Json> getPaperPageList(Paper paper, Integer page, Integer rows, Integer teacherId);

	Integer getPaperTotal(Paper paper);

	boolean saveRelation(Integer paperId, Integer classesId);

	boolean checkPaper(String title, Integer grade, Integer teacherId);

	List<Paper> getTopics(Integer classesId);

	List<Paper4Json> getReviewPageList(Paper paper, Integer page, Integer rows,
			Integer departmentId);

	Integer getReviewPaperTotal(Paper paper, Integer departmentId);

	List<Paper> getAllPaper(Paper paper, Integer page, Integer rows,
			Integer classesId);

	Integer getAllPaperTotal(Paper paper, Integer classesId);

}
