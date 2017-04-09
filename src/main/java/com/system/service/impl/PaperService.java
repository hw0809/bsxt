package com.system.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.dao.IPaperDao;
import com.system.entity.Paper;
import com.system.entity4Json.Paper4Json;
import com.system.service.IPaperService;

@Service
@Transactional
public class PaperService extends BaseService<Paper, Integer> implements
		IPaperService {

	@Resource
	private IPaperDao dao;

	@PostConstruct
	public void init() {
		super.setDao(dao);
	}

	@Override
	public List<Paper4Json> getPaperPageList(Integer teacherId, Paper paper,
			Integer page, Integer rows) {
		return dao.getPaperPageList(teacherId, paper, page, rows);
	}

	@Override
	public Integer getPaperTotal(Paper paper) {
		return dao.getPaperTotal(paper);
	}

	@Override
	public boolean saveRelation(Integer paperId, Integer classesId) {
		return dao.saveRelation(paperId, classesId);
	}

	@Override
	public boolean checkPaper(String title, Integer grade, Integer teacherId) {
		return dao.checkPaper(title, grade, teacherId);
	}

	@Override
	public List<Paper> getTopics(Integer classesId) {
		return dao.getTopics(classesId);
	}

	@Override
	public List<Paper4Json> getReviewPageList(Paper paper, Integer page,
			Integer rows, Integer departmentId) {
		return dao.getReviewPageList(paper, page, rows, departmentId);
	}

	@Override
	public Integer getReviewPaperTotal(Paper paper, Integer departmentId) {
		return dao.getReviewPaperTotal(paper, departmentId);
	}

	@Override
	public List<Paper> getAllPaper(Paper paper, Integer page, Integer rows,
			Integer classesId) {
		return dao.getAllPaper(paper, page, rows, classesId);
	}

	@Override
	public Integer getAllPaperTotal(Paper paper, Integer classesId) {
		return dao.getAllPaperTotal(paper, classesId);
	}

	@Override
	public List<Paper4Json> getPaperPageList(Paper paper, Integer page,
			Integer rows, Integer teacherId) {
		return dao.getPaperPageList(paper, page, rows, teacherId);
	}
}
