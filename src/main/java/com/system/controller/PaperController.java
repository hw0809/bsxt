package com.system.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.system.controller.base.BaseController;
import com.system.entity.Adminstrator;
import com.system.entity.Classes;
import com.system.entity.LoginInformation;
import com.system.entity.Paper;
import com.system.entity.Student;
import com.system.entity.Teacher;
import com.system.entity4Json.Paper4Json;
import com.system.entity4Json.Result4Json;
import com.system.entity4enum.Paper4Enum;
import com.system.jqgriddata.JQGridResult;
import com.system.service.IAdminstratorService;
import com.system.service.IClassesService;
import com.system.service.IPaperService;
import com.system.service.IStudentService;
import com.system.service.ITeacherService;
import com.system.util.FileUtil;
import com.system.util.ReadProp;
import com.system.util.StringUtil;

@Controller
@RequestMapping("/paper")
public class PaperController extends BaseController {

	@Resource
	private ITeacherService teacherService;

	@Resource
	private IPaperService paperService;

	@Resource
	private IClassesService classesService;

	@Resource
	private IStudentService studentService;

	@Resource
	private IAdminstratorService adminstratorService;

	// 上传的路径
	private static String upladPath = ReadProp.getKeyValue("upload");

	/**
	 * 学生端,我的选题
	 * 
	 * @return
	 */
	@RequestMapping(value = "myPaperPage", method = RequestMethod.GET)
	public ModelAndView myPaperPage(HttpSession session) {
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Student student = studentService.findByAccount(account);
		Map<String, Object> model = new HashMap<String, Object>();
		if (student.getTeacher() != null && student.getPaperReview() == 2
				&& student.getPaper() != null) {
			Paper paper = student.getPaper();
			Teacher teacher = student.getTeacher();
			model.put("paper", paper);
			model.put("teacher", teacher);

			// 开题报告url
			if (student.getThesisProposal() != null) {
				model.put("thesisProposal", 1);
			} else {
				model.put("thesisProposal", 0);
			}
			// 中期检查
			if (student.getInspection() != null) {
				model.put("inspection", 1);
			} else {
				model.put("inspection", 0);
			}
			// 毕业论文
			if (student.getThesis() != null) {
				model.put("thesis", 1);
			} else {
				model.put("thesis", 0);
			}

			return new ModelAndView("paper/paper_myPaperPage", model);
		} else {
			return new ModelAndView("paper/paper_noChoosePaper");
		}

	}

	@RequestMapping(value = "submitPaperPage", method = RequestMethod.GET)
	public ModelAndView submitPaperPage(HttpSession session) {
		List<Classes> classes = null;
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Teacher teacher = teacherService.findByAccount(account);
		classes = classesService.getDepartmentById(teacher.getDepartment()
				.getId());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("classes", classes);
		return new ModelAndView("paper/paper_add", model);
	}

	/**
	 * 教师端拿到学生开题报告
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "getThesisProposalReport", method = RequestMethod.GET)
	public ModelAndView getThesisProposalReport(String account,
			HttpSession session) {
		Student student = studentService.findByAccount(account);

		if (student.getThesisProposalEvaluate() == null) {
			student.setThesisProposalEvaluate("");
		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("student", student);

		return new ModelAndView("paper/paper_thesisProposalReport", model);
	}

	/**
	 * 教师端拿到学生毕业设计论文
	 * 
	 * @param account
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "getThesisReport", method = RequestMethod.GET)
	public ModelAndView getThesisReport(String account, HttpSession session) {
		Student student = studentService.findByAccount(account);

		if (student.getThesisEvaluate() == null) {
			student.setThesisEvaluate("");
		}

		/*
		 * if (student.getThesisScore() == null) { student.setThesisScore(null);
		 * }
		 */

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("student", student);

		return new ModelAndView("paper/paper_thesisReport", model);
	}

	@RequestMapping(value = "checkThesis", method = RequestMethod.GET)
	public ModelAndView checkThesis(HttpSession session) {
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Student student = studentService.findByAccount(account);

		if (student.getThesisEvaluate() == null) {
			student.setThesisEvaluate("还没有评价");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("student", student);

		return new ModelAndView("paper/paper_checkThesis", model);
	}

	@RequestMapping(value = "checkThesisProposal", method = RequestMethod.GET)
	public ModelAndView checkThesisProposal(HttpSession session) {
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Student student = studentService.findByAccount(account);

		if (student.getThesisProposalEvaluate() == null) {
			student.setThesisProposalEvaluate("还没有评价");
		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("student", student);

		return new ModelAndView("paper/paper_checkThesisProposal", model);
	}

	@RequestMapping(value = "myStudentsPaperPage", method = RequestMethod.GET)
	public ModelAndView myStudentsPaperPage(HttpSession session) {
		return new ModelAndView("paper/paper_myStudentsPaperPage");
	}

	@RequestMapping(value = "reviewPaperPage", method = RequestMethod.GET)
	public ModelAndView reviewPaperPage(HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Adminstrator adminstrator = adminstratorService.findByAccount(account);
		model.put("departmentId", adminstrator.getDepartment().getId());
		return new ModelAndView("paper/paper_reviewPaper", model);
	}

	@RequestMapping(value = "teacherPaperPage", method = RequestMethod.GET)
	public ModelAndView teacherPaperPage(HttpSession session) {
		return new ModelAndView("paper/paper_teacherPaper");
	}

	@RequestMapping(value = "getDetail", method = RequestMethod.GET)
	public ModelAndView submitPaperPage(Integer id, HttpSession session) {
		Paper paper = paperService.findById(id);
		Map<String, Object> model = new HashMap<String, Object>();
		String classesName = "";
		if (paper.getClasses() != null) {
			Set<Classes> set = paper.getClasses();
			for (Classes item : set) {
				classesName += item.getName() + ";";
			}
		}
		classesName = classesName.substring(0, classesName.length() - 1);
		model.put("paper", paper);
		model.put("classesName", classesName);
		return new ModelAndView("paper/paper_detail", model);
	}

	/**
	 * 学生端查看选题详情并选择和退选
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "studentPaperDetailId", method = RequestMethod.GET)
	public ModelAndView studentPaperDetailId(Integer id, HttpSession session) {
		Paper paper = paperService.findById(id);
		Map<String, Object> model = new HashMap<String, Object>();

		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Student student = studentService.findByAccount(account);

		if (student.getPaperReview() == null) {
			student.setPaperReview(0);
		}

		List<Student> students = paper.getStudents();
		Integer maxNum = 0;
		if (students == null) {
			maxNum = 0;
		} else if (students.isEmpty()) {
			maxNum = 0;
		} else if (paper.getNum() == students.size()) {
			maxNum = paper.getNum();
		}

		model.put("maxNum", maxNum);
		model.put("paper", paper);
		model.put("student", student);

		return new ModelAndView("paper/paper_studentPaperDetail", model);
	}

	/**
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "getMyStudentsDetail", method = RequestMethod.GET)
	public ModelAndView getMyStudentsDetail(Integer id, HttpSession session) {
		Student student = studentService.findById(id);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("student", student);

		// 等于2表示老师确认了
		if (student.getPaperReview() == 2) {
			// 跟查看学生一样，只不过按钮可以调到学生上传开题报告，中期检查，终稿的按钮
			// 开题报告url
			if (student.getThesisProposal() != null) {
				model.put("thesisProposal", 1);
			} else {
				model.put("thesisProposal", 0);
			}
			// 中期检查
			if (student.getInspection() != null) {
				model.put("inspection", 1);
			} else {
				model.put("inspection", 0);
			}
			// 毕业论文
			if (student.getThesis() != null) {
				model.put("thesis", 1);
			} else {
				model.put("thesis", 0);
			}
			return new ModelAndView("paper/paper_myStudentsSubmitPage", model);
		} else {
			return new ModelAndView("paper/paper_myStudentsDetail", model);
		}
	}

	@RequestMapping(value = "allPaperPage", method = RequestMethod.GET)
	public ModelAndView topicPage(Integer id, HttpSession session) {
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Student student = studentService.findByAccount(account);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("studentId", student.getId());
		return new ModelAndView("paper/paper_allPaperPage", model);
	}

	@RequestMapping(value = "studentPaperPage", method = RequestMethod.POST)
	public @ResponseBody
	JQGridResult<Paper4Json> studentPaperPage(String title,
			HttpSession session, HttpServletRequest request) {
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Student student = studentService.findByAccount(account);
		Integer classesId = student.getClasses().getId();

		Paper paper = new Paper();
		if (title != null && !StringUtil.isNullOrEmpty(title)) {
			paper.setTitle(title);
		}

		List<Paper> papers = paperService.getAllPaper(paper,
				super.getPage(request), super.getRows(request), classesId);
		Integer total = paperService.getAllPaperTotal(paper, classesId);

		List<Paper4Json> jsons = new ArrayList<Paper4Json>();
		Paper4Json json = null;
		for (Paper item : papers) {
			json = new Paper4Json();
			json.setId(item.getId());
			json.setTitle(item.getTitle());
			json.setNum(item.getNum());
			if (item.getTeacher().getId() != null) {
				Teacher teacher = teacherService.findById(item.getTeacher()
						.getId());
				json.setTeacherName(teacher.getName());
			}
			List<Student> students = item.getStudents();
			Integer choose = 0;
			if (!students.isEmpty()) {
				for (Student s : students) {
					if (s.getId().equals(student.getId())) {
						if (student.getPaperReview() == 1) {
							// 1要等待教师确认
							choose = 1;
						} else if (student.getPaperReview() == 2) {
							// 2已经选择
							choose = 2;
						} else if (student.getPaperReview() == 0) {
							choose = 0;
						}
					}
				}
			}
			json.setPaperReview(choose);

			String maxNum = "";
			// 选题人数是否已满
			if (students.isEmpty()) {
				maxNum = "未满";
			} else if (students.size() == item.getNum()) {
				maxNum = "人数已满";
			} else {
				maxNum = "未满";
			}
			json.setMaxNum(maxNum);

			jsons.add(json);
		}

		JQGridResult<Paper4Json> rs = new JQGridResult<Paper4Json>();
		rs.setRows(jsons);
		rs.setPage(super.getPage(request));
		rs.setTotal(super.getTotal(super.getRows(request), total));
		rs.setRecords(total);
		return rs;
	}

	@RequestMapping(value = "getPaperNum", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json getPaperNum(HttpSession session) {
		String num = ReadProp.getKeyValue("paperNum");
		return new Result4Json(true, num);
	}

	@RequestMapping(value = "getPaperGrade", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json getPaperGrade(HttpSession session) {
		String grade = ReadProp.getKeyValue("paperGrade");
		return new Result4Json(true, grade);
	}

	public boolean checkPaper(String title, Integer grade, Integer teacherId) {
		return paperService.checkPaper(title, grade, teacherId);
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json savePaper(String title, Integer num, String desc, String ids,
			Integer grade, HttpSession session) {
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Teacher teacher = teacherService.findByAccount(account);
		if (checkPaper(title, grade, teacher.getId())) {
			return new Result4Json(false, "您已经提交过该选题.");
		}

		Paper paper = new Paper();
		paper.setTitle(title);
		paper.setNum(num);
		paper.setDesc(desc);
		paper.setGrade(grade);
		paper.setTitleReview(Paper4Enum.NOREVIEW);

		paper.setTeacher(teacher);

		try {
			paperService.save(paper);

		} catch (Exception e) {
			e.printStackTrace();
			return new Result4Json(false, "保存失败");
		}
		Integer paperId = paper.getId();
		if (ids != null) {
			String[] classesId = ids.split("##");
			for (String id : classesId) {
				paperService.saveRelation(paperId, Integer.valueOf(id));
			}
		}
		return new Result4Json(true);
	}

	@RequestMapping("getPageList")
	public @ResponseBody
	JQGridResult<Paper4Json> getPageList(String title, Integer grade,
			Integer titleReview, HttpServletRequest request, HttpSession session) {
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Teacher teacher = teacherService.findByAccount(account);

		Paper paper = new Paper();
		if (!StringUtil.isNullOrEmpty(title)) {
			paper.setTitle(title);
		}
		if (grade != null) {
			paper.setGrade(grade);
		} else {
			Calendar calendar = Calendar.getInstance();
			Integer g = calendar.get(Calendar.YEAR);
			paper.setGrade(g);
		}
		if (titleReview == null) {
			paper.setTitleReview(3);
		} else {
			paper.setTitleReview(titleReview);
		}
		List<Paper4Json> papers = paperService.getPaperPageList(
				teacher.getId(), paper, super.getPage(request),
				super.getRows(request));
		Integer total = paperService.getPaperTotal(paper);

		JQGridResult<Paper4Json> rs = new JQGridResult<Paper4Json>();
		rs.setRows(papers);
		rs.setPage(super.getPage(request));
		rs.setTotal(super.getTotal(super.getRows(request), total));
		rs.setRecords(total);
		return rs;
	}

	/**
	 * 拿到选我论文题目的学生列表，论文题目，学生姓名，点击学生姓名可以跳页面确定学生选择
	 * 
	 * @param title
	 * @param grade
	 * @param titleReview
	 * @param request
	 * @return
	 */
	@RequestMapping("getMyStudentsPageList")
	public @ResponseBody
	JQGridResult<Paper4Json> getMyStudentsPageList(Integer grade,
			HttpServletRequest request, HttpSession session) {
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Teacher teacher = teacherService.findByAccount(account);
		Paper paper = new Paper();
		if (grade != null) {
			paper.setGrade(grade);
		} else {
			Calendar calendar = Calendar.getInstance();
			Integer g = calendar.get(Calendar.YEAR);
			paper.setGrade(g);
		}
		paper.setTitleReview(1);
		List<Paper4Json> papers = paperService
				.getPaperPageList(paper, super.getPage(request),
						super.getRows(request), teacher.getId());
		Integer total = paperService.getPaperTotal(paper);

		for (Paper4Json item : papers) {
			Paper p = paperService.findById(item.getId());
			List<Student> students = p.getStudents();
			String info = "";
			if (students != null && !students.isEmpty()) {
				for (Student s : students) {
					info += s.getId() + "##" + s.getName() + ",";
				}
			}
			if (info != "") {
				info = info.substring(0, info.length() - 1);
			}
			item.setStudentInfo(info);
		}

		JQGridResult<Paper4Json> rs = new JQGridResult<Paper4Json>();
		rs.setRows(papers);
		rs.setPage(super.getPage(request));
		rs.setTotal(super.getTotal(super.getRows(request), total));
		rs.setRecords(total);
		return rs;
	}

	@RequestMapping("getReviewPageList")
	public @ResponseBody
	JQGridResult<Paper4Json> getReviewPageList(String title, Integer grade,
			String classes, Integer titleReview, HttpServletRequest request,
			HttpSession session) {
		Paper paper = new Paper();
		if (!StringUtil.isNullOrEmpty(title)) {
			paper.setTitle(title);
		}
		if (grade != null) {
			paper.setGrade(grade);
		} else {
			Calendar calendar = Calendar.getInstance();
			Integer g = calendar.get(Calendar.YEAR);
			paper.setGrade(g);
		}
		/*
		 * if (titleReview != null) { paper.setTitleReview(titleReview); }
		 */
		// 3查全部
		if (titleReview == null) {
			paper.setTitleReview(3);
		} else {
			paper.setTitleReview(titleReview);
		}
		if (classes != null && !StringUtil.isNullOrEmpty(classes)) {
			Classes c = new Classes();
			c.setName(classes);
			Integer classesId = classesService.getClassesId(classes, grade);
			c.setId(classesId);
			Set<Classes> set = new HashSet<Classes>();
			set.add(c);
			paper.setClasses(set);
		}
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Adminstrator adminstrator = adminstratorService.findByAccount(account);
		Integer departmentId = adminstrator.getDepartment().getId();

		List<Paper4Json> papers = paperService.getReviewPageList(paper,
				super.getPage(request), super.getRows(request), departmentId);

		Integer total = paperService.getReviewPaperTotal(paper, departmentId);

		JQGridResult<Paper4Json> rs = new JQGridResult<Paper4Json>();
		rs.setRows(papers);
		rs.setPage(super.getPage(request));
		rs.setTotal(super.getTotal(super.getRows(request), total));
		rs.setRecords(total);
		return rs;
	}

	@RequestMapping(value = "getClasses", method = RequestMethod.POST)
	public @ResponseBody
	List<Classes> getClasses(HttpSession session) {
		List<Classes> classes = null;
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Teacher teacher = teacherService.findByAccount(account);
		classes = classesService.getDepartmentById(teacher.getDepartment()
				.getId());
		return classes;
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json del(Integer id) {
		Paper paper = paperService.findById(id);
		boolean flag = false;

		if (paper.getTitleReview() == 1) {
			return new Result4Json(false, "已过审，不能删除", "");
		}
		flag = paperService.deleteById(paper.getId());
		if (flag) {
			return new Result4Json(true, "删除成功", "");
		} else {
			return new Result4Json(false, "删除失败", "");
		}
	}

	@RequestMapping(value = "reviewPaper", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json reviewPaper(Integer review, String apperise, String title,
			Integer id, String desc, Integer num, HttpSession session) {
		boolean flag = false;
		Paper paper = paperService.findById(id);
		if (!StringUtil.isNullOrEmpty(apperise)) {
			paper.setApperise(apperise);
		}
		paper.setTitleReview(review);
		try {
			flag = paperService.modify(paper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag) {
			return new Result4Json(true);
		} else {
			return new Result4Json(false);
		}
	}

	/**
	 * 通过学生选题
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "passStudentPaper", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json passStudentPaper(Integer id, HttpSession session) {
		Student student = studentService.findById(id);

		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Teacher teacher = teacherService.findByAccount(account);

		student.setTeacher(teacher);
		student.setPaperReview(2);

		boolean flag = false;

		try {
			flag = studentService.modify(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag) {
			return new Result4Json(true);
		} else {
			return new Result4Json(false);
		}
	}

	/**
	 * 退掉学生选题
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "returnStudentPaper", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json returnStudentPaper(Integer id, HttpSession session) {
		Student student = studentService.findById(id);

		student.setPaper(null);
		student.setPaperReview(null);

		boolean flag = false;
		try {
			flag = studentService.modify(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag) {
			return new Result4Json(true);
		} else {
			return new Result4Json(false);
		}
	}

	/**
	 * 学生选择题目
	 * 
	 * @param review
	 * @param apperise
	 * @param title
	 * @param id
	 * @param desc
	 * @param num
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "studentReviewPaper", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json studentReviewPaper(Integer studenRteview, Integer id,
			HttpSession session) {
		boolean flag = false;

		Paper paper = paperService.findById(id);

		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Student student = studentService.findByAccount(account);

		student.setPaper(paper);
		student.setPaperReview(1);

		try {
			flag = studentService.modify(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag) {
			return new Result4Json(true);
		} else {
			return new Result4Json(false);
		}
	}

	/**
	 * 取消选题
	 * 
	 * @param studenRteview
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "studentRemoveReviewPaper", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json studentRemoveReviewPaper(Integer studenRteview, Integer id,
			HttpSession session) {
		boolean flag = false;

		// Paper paper = paperService.findById(id);

		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Student student = studentService.findByAccount(account);

		student.setPaper(null);
		student.setPaperReview(null);

		try {
			flag = studentService.modify(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag) {
			return new Result4Json(true);
		} else {
			return new Result4Json(false);
		}
	}

	/**
	 * 学生查看论文方法
	 * 
	 * @return
	 */
	@RequestMapping(value = "getTopicPage", method = RequestMethod.POST)
	public/* JQGridResult<Paper> */void getTopicPage(HttpSession session) {

		/*
		 * LoginInformation loginInfo = (LoginInformation) session
		 * .getAttribute("loginInfo"); String account = loginInfo.getAccount();
		 * Student student = studentService.findByAccount(account); // Paper
		 * paper = student.getClasses().getPaper(); Integer classesId =
		 * student.getClasses().getId(); paperService.getTopics(classesId);
		 * List<Topic4Json> jsons = new ArrayList<Topic4Json>();
		 */

		/*
		 * for (Topic4Json topic4Json : jsons) {
		 * 
		 * }
		 */
		// return new JQGridResult<Paper>();
	}

	/**
	 * 上传开题报告
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "uploadThesisProposal", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json uploadThesisProposal(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Student student = studentService.findByAccount(account);

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("thesisProposalUpfile");
		if (file.isEmpty()) {
			throw new Exception("文件不存在！");
		}

		String suffix = file.getOriginalFilename().substring(
				file.getOriginalFilename().lastIndexOf("."));
		InputStream in = null;
		in = file.getInputStream();

		String temp = new String(file.getOriginalFilename().getBytes(
				"ISO-8859-1"), "UTF-8");

		String fileName = temp.substring(0, temp.indexOf("."));

		in = file.getInputStream();

		String path = upladPath + new Date().getTime() + fileName + account;

		FileUtil.uploadFile(in, path + suffix, account);

		student.setThesisProposalTitle(fileName);
		student.setThesisProposal(path + suffix);

		studentService.modify(student);

		return new Result4Json(true);
	}

	/**
	 * 上传毕业论文
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "uploadThesis", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json uploadThesis(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Student student = studentService.findByAccount(account);

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("thesisUpfile");
		if (file.isEmpty()) {
			throw new Exception("文件不存在！");
		}

		String suffix = file.getOriginalFilename().substring(
				file.getOriginalFilename().lastIndexOf("."));
		InputStream in = null;
		in = file.getInputStream();

		String temp = new String(file.getOriginalFilename().getBytes(
				"ISO-8859-1"), "UTF-8");

		String fileName = temp.substring(0, temp.indexOf("."));

		in = file.getInputStream();

		String path = upladPath + new Date().getTime() + fileName + account;

		FileUtil.uploadFile(in, path + suffix, account);

		student.setThesisTitle(fileName);
		student.setThesis(path + suffix);

		studentService.modify(student);

		return new Result4Json(true);
	}

	/**
	 * 下载开题报告
	 * 
	 * @param account
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "downReport")
	public ResponseEntity<byte[]> downReport(String account,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException {
		Student student = studentService.findByAccount(account);
		String fileName = student.getThesisProposal().split("\\\\", -1)[2];

		String path = student.getThesisProposal();
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();
		fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
				headers, HttpStatus.CREATED);
	}

	/**
	 * 下载毕业论文
	 * 
	 * @param account
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "downThesisReport")
	public ResponseEntity<byte[]> downThesisReport(String account,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException {
		Student student = studentService.findByAccount(account);
		String fileName = student.getThesis().split("\\\\", -1)[2];

		String path = student.getThesis();
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();
		fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
				headers, HttpStatus.CREATED);
	}

	/**
	 * 提交开题报告评价
	 * 
	 * @param account
	 * @param evaluate
	 */
	@RequestMapping(value = "saveThesisProposalEvaluate", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json saveThesisProposalEvaluate(String account, String evaluate) {
		Student student = studentService.findByAccount(account);
		if ("".equals(evaluate)) {
			student.setThesisProposalEvaluate(null);
		} else {
			student.setThesisProposalEvaluate(evaluate);
		}
		studentService.modify(student);
		return new Result4Json(true);
	}

	/**
	 * 提交毕业论文评价
	 * 
	 * @param account
	 * @param evaluate
	 * @return
	 */
	@RequestMapping(value = "saveThesisEvaluate", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json saveThesisEvaluate(Integer score, String account,
			String evaluate) {
		Student student = studentService.findByAccount(account);
		if ("".equals(evaluate)) {
			student.setThesisEvaluate(null);
		} else {
			student.setThesisEvaluate(evaluate);
		}

		if (score != null) {
			student.setThesisScore(score);
		}
		studentService.modify(student);
		return new Result4Json(true);
	}

	@RequestMapping(value = "getTeacherDetail", method = RequestMethod.GET)
	public ModelAndView getTeacherDetail(Integer id, HttpSession session) {
		Paper paper = paperService.findById(id);
		Map<String, Object> model = new HashMap<String, Object>();

		Teacher teacher = paper.getTeacher();

		model.put("teacher", teacher);
		return new ModelAndView("paper/paper_teacherDetail", model);
	}
}
