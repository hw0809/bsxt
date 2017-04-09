package com.system.controller;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.system.controller.base.BaseController;
import com.system.entity.Adminstrator;
import com.system.entity.Classes;
import com.system.entity.Group;
import com.system.entity.LoginInformation;
import com.system.entity.Student;
import com.system.entity.Teacher;
import com.system.entity4Json.Group4Json;
import com.system.entity4Json.Result4Json;
import com.system.entity4Json.StudentInfo4Json;
import com.system.jqgriddata.JQGridResult;
import com.system.service.IAdminstratorService;
import com.system.service.IClassesService;
import com.system.service.IGroupService;
import com.system.service.IStudentService;
import com.system.service.ITeacherService;

@Controller
@RequestMapping("/group")
public class GroupController extends BaseController {

	@Resource
	private ITeacherService teacherService;

	@Resource
	private IAdminstratorService adminstratorService;

	@Resource
	private IStudentService studentService;

	@Resource
	private IClassesService classesService;

	@Resource
	private IGroupService groupService;
	
	/**
	 * 教师端查看答辩小组学生
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "studentsPage", method = RequestMethod.GET)
	public ModelAndView reviewStudentsPage(HttpSession session) {
		return new ModelAndView("group/group_studentsPage");
	}

	@RequestMapping(value = "addGroupPage", method = RequestMethod.GET)
	public ModelAndView addGroupPage(HttpSession session) {
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Adminstrator adminstrator = adminstratorService.findByAccount(account);

		List<Teacher> teachers = teacherService
				.getTeachersByDepartment(adminstrator.getDepartment().getId());
		
		Iterator<Teacher> it = teachers.iterator();
		while(it.hasNext()){
			Teacher teacher = it.next();
		    if(teacher.getGroups().size() != 0){
		        it.remove();
		    }
		}

		List<Student> students = studentService
				.getStudentsByDepartment(adminstrator.getDepartment().getId());

		List<Classes> classes = classesService
				.getClassesByDepartment(adminstrator.getDepartment().getId());

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("teachers", teachers);
		model.put("students", students);
		model.put("classes", classes);

		return new ModelAndView("group/group_add", model);
	}

	/**
	 * 答辩小组页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "groupPage", method = RequestMethod.GET)
	public ModelAndView groupPage() {
		return new ModelAndView("group/group_groupPage");
	}
	
	@RequestMapping(value = "getStudents", method = RequestMethod.GET)
	public @ResponseBody
	Result4Json getStudents(Integer classesId) {
		List<Student> students = studentService
				.getStudentsByClassesId(classesId);
		List<StudentInfo4Json> jsons = new ArrayList<StudentInfo4Json>();
		StudentInfo4Json json = null;
		for (Student student : students) {
			if (student.getGroup() == null) {
				json = new StudentInfo4Json();
				json.setId(student.getId());
				json.setName(student.getName());
				jsons.add(json);
			}
		}
		return new Result4Json(true, jsons);
	}

	/**
	 * 保存答辩小组
	 * 
	 * @param name
	 * @param teachers
	 * @param students
	 * @param grade
	 * @param session
	 */
	@RequestMapping(value = "saveGroup", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json saveGroup(String name, String teachers, String students,
			Integer grade, HttpSession session) {
		String[] studentId = students.split(",", -1);
		String[] teacherId = teachers.split(",", -1);

		Set<Teacher> teacherSet = new HashSet<Teacher>();
		if (teacherId != null) {
			Teacher teahcer = null;
			for (String id : teacherId) {
				teahcer = teacherService.findById(Integer.valueOf(id));
				teacherSet.add(teahcer);
			}
		}

		List<Student> studentList = new ArrayList<Student>();
		if (studentId != null) {
			Student student = null;
			for (String id : studentId) {
				student = studentService.findById(Integer.valueOf(id));
				studentList.add(student);
			}
		}

		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Adminstrator adminstrator = adminstratorService.findByAccount(account);

		Group group = new Group();
		group.setName(name);
		group.setDepartment(adminstrator.getDepartment());

		if (grade == null) {
			Calendar a = Calendar.getInstance();
			group.setGrade(a.get(Calendar.YEAR));
		} else {
			group.setGrade(grade);
		}
		groupService.save(group);

		for (Student student : studentList) {
			student.setGroup(group);
			studentService.modify(student);
		}

		Set<Group> groups = new HashSet<Group>();
		groups.add(group);
		for (Teacher teacher : teacherSet) {
			teacher.setGroups(groups);
			teacherService.modify(teacher);
		}

		return new Result4Json(true);
	}

	@RequestMapping("getGroup")
	public @ResponseBody
	JQGridResult<Group4Json> getGroup(Integer grade,
			HttpServletRequest request, HttpSession session) {
		Group group = new Group();

		if (grade != null) {
			group.setGrade(grade);
		} else {
			Calendar calendar = Calendar.getInstance();
			Integer g = calendar.get(Calendar.YEAR);
			group.setGrade(g);
		}

		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Adminstrator adminstrator = adminstratorService.findByAccount(account);
		Integer departmentId = adminstrator.getDepartment().getId();

		List<Group> groups = groupService.getGroups(departmentId, group,
				super.getPage(request), super.getRows(request));

		Integer total = groupService.getGroupTotal(departmentId, group);

		List<Group4Json> jsons = new ArrayList<Group4Json>();
		Group4Json json = null;
		for (Group item : groups) {
			json = new Group4Json();
			json.setId(item.getId());
			json.setName(item.getName());

			List<Student> students = item.getStudents();
			String info = "";
			for (Student s : students) {
				info += s.getId() + "##" + s.getName() + ",";
			}
			if (info != "") {
				info = info.substring(0, info.length() - 1);
			}
			json.setStudentInfo(info);

			Set<Teacher> teachers = item.getTeachers();
			String tInfo = "";
			for (Teacher t : teachers) {
				tInfo += t.getId() + "##" + t.getName() + ",";
			}
			if (tInfo != "") {
				tInfo = tInfo.substring(0, tInfo.length() - 1);
			}
			json.setTeacherInfo(tInfo);
			jsons.add(json);
		}

		JQGridResult<Group4Json> rs = new JQGridResult<Group4Json>();
		rs.setRows(jsons);
		rs.setPage(super.getPage(request));
		rs.setTotal(super.getTotal(super.getRows(request), total));
		rs.setRecords(total);
		return rs;
	}
	
	@RequestMapping("getGroupStudents")
	public @ResponseBody
	JQGridResult<StudentInfo4Json> getGroupStudents(Integer grade,
			HttpServletRequest request, HttpSession session) {
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Teacher teacher = teacherService.findByAccount(account);
		if (grade == null) {
			Calendar calendar = Calendar.getInstance();
			Integer g = calendar.get(Calendar.YEAR);
			grade = g;
		}

		List<Student> students = groupService.getGroupStudents(teacher
				.getDepartment().getId(), grade, teacher.getId());

		List<StudentInfo4Json> jsons = new ArrayList<StudentInfo4Json>();
		if (students != null) {
			StudentInfo4Json json = null;
			for (Student item : students) {
				json = new StudentInfo4Json();
				json.setId(item.getId());
				json.setName(item.getName());
				jsons.add(json);
			}

			JQGridResult<StudentInfo4Json> rs = new JQGridResult<StudentInfo4Json>();
			rs.setRows(jsons);
			rs.setPage(super.getPage(request));
			rs.setTotal(super.getTotal(super.getRows(request), students.size()));
			rs.setRecords(students.size());
			return rs;
		} else {
			JQGridResult<StudentInfo4Json> rs = new JQGridResult<StudentInfo4Json>();
			rs.setRows(jsons);
			rs.setPage(super.getPage(request));
			rs.setTotal(super.getTotal(super.getRows(request), 0));
			rs.setRecords(0);
			return rs;
		}
	}
	
	@RequestMapping(value = "getGroupStudentDetail", method = RequestMethod.GET)
	public ModelAndView getGroupStudentDetail(Integer studentId,
			HttpSession session) {
		Student student = studentService.findById(studentId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("student", student);
		
		if (student.getPaperReview() == null) {
			return new ModelAndView("reviewGroup/reviewGroup_noThesis");
		}

		// 等于2表示老师确认了
		if (student.getPaperReview() == 2) {
			// 跟查看学生一样，只不过按钮可以调到学生上传开题报告，中期检查，终稿的按钮
			// 开题报告url
			// 毕业论文
			if (student.getThesis() != null) {
				model.put("thesis", 1);
			} else {
				model.put("thesis", 0);
			}
			return new ModelAndView(
					"group/group_groupStudentThesis", model);
		} else {
			return new ModelAndView("reviewGroup/reviewGroup_noThesis");
		}
	}
	
	@RequestMapping("saveGroupStudentScore")
	public @ResponseBody
	Result4Json saveGroupStudentScore(Integer id, Integer score,
			String evaluate, HttpServletRequest request, HttpSession session) {
		Student student = studentService.findById(id);
		student.setGroupScore(score);
		student.setGroupEvaluate(evaluate);
		studentService.modify(student);
		return new Result4Json(true);
	}
	
	
}
