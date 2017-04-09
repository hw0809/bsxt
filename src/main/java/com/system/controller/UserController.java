package com.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.system.entity.Adminstrator;
import com.system.entity.LoginInformation;
import com.system.entity.Student;
import com.system.entity.Teacher;
import com.system.entity4Json.AdminstratorInfo4Json;
import com.system.entity4Json.Result4Json;
import com.system.entity4Json.StudentInfo4Json;
import com.system.entity4Json.TeacherInfo4Json;
import com.system.entity4enum.LoginInformation4Enum;
import com.system.service.IAdminstratorService;
import com.system.service.IStudentService;
import com.system.service.ITeacherService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private IStudentService studentService;

	@Resource
	private ITeacherService teacherService;

	@Resource
	private IAdminstratorService adminstratorService;

	/**
	 * 返回个人信息
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "userInfo", method = RequestMethod.GET)
	public ModelAndView getUserInfoPage(HttpSession session) {
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");

		StudentInfo4Json sJson = null;
		TeacherInfo4Json tJson = null;
		AdminstratorInfo4Json aJson = null;

		Integer loginType = loginInfo.getLoginType();
		switch (loginType) {
		case LoginInformation4Enum.STUDENT:
			Student student = studentService.findByAccount(loginInfo
					.getAccount());
			sJson = new StudentInfo4Json();
			sJson.setId(student.getId());
			sJson.setName(student.getName());
			sJson.setAccount(student.getAccount());
			sJson.setDepartmentName(student.getDepartment().getName());
			sJson.setGrade(student.getGrade());
			sJson.setClassName(student.getClasses().getName());
			sJson.setEmail(student.getEmail());
			sJson.setTel(student.getTel());
			sJson.setDesc(student.getDesc());
			break;
		case LoginInformation4Enum.TEACHER:
			Teacher teacher = teacherService.findByAccount(loginInfo
					.getAccount());
			tJson = new TeacherInfo4Json();
			tJson.setId(teacher.getId());
			tJson.setAccount(teacher.getAccount());
			tJson.setDepartmentName(teacher.getDepartment().getName());
			tJson.setName(teacher.getName());
			tJson.setDesc(teacher.getDesc());
			tJson.setEmail(teacher.getEmail());
			tJson.setTel(teacher.getTel());
			break;
		case LoginInformation4Enum.ADMINISTRATOR:
			Adminstrator adminstrator = adminstratorService
					.findByAccount(loginInfo.getAccount());
			aJson = new AdminstratorInfo4Json();
			aJson.setName(adminstrator.getName());
			aJson.setAccount(adminstrator.getAccount());
			break;
		}
		if (loginType.intValue() == LoginInformation4Enum.STUDENT) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("student", sJson);
			return new ModelAndView("/user/studentInfo", model);
		} else if (loginType.intValue() == LoginInformation4Enum.TEACHER) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("teacher", tJson);
			return new ModelAndView("/user/teacherInfo", model);
		} else {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("adminstrator", aJson);
			return new ModelAndView("/user/adminstratorInfo", model);
		}
	}

	@RequestMapping(value = "saveStudent", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json saveStudent(Integer id, String email, String tel, String desc) {
		Student old = this.studentService.findById(id);
		if (old == null) {

		}
		old.setEmail(email);
		old.setTel(tel);
		old.setDesc(desc);
		try {
			this.studentService.modify(old);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new Result4Json(false, "修改失败", "");
		}
		return new Result4Json(true, "修改成功", "");
	}

	@RequestMapping(value = "saveTeacher", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json saveTeacher(Integer id, String email, String tel, String desc) {
		Teacher old = this.teacherService.findById(id);
		if (old == null) {

		}
		old.setEmail(email);
		old.setTel(tel);
		old.setDesc(desc);
		try {
			this.teacherService.modify(old);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new Result4Json(false, "修改失败", "");
		}
		return new Result4Json(true, "修改成功", "");
	}
}
