package com.system.controller;

import java.util.HashMap;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.system.bean.LoginInformatin4Bean;
import com.system.entity.Adminstrator;
import com.system.entity.LoginInformation;
import com.system.entity.Student;
import com.system.entity.Teacher;
import com.system.entity4Json.Result4Json;
import com.system.entity4enum.LoginInformation4Enum;
import com.system.service.IAdminstratorService;
import com.system.service.ILoginInformationService;
import com.system.service.IStudentService;
import com.system.service.ITeacherService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Resource
	private ILoginInformationService loginInformationService;

	@Resource
	private IStudentService studentService;

	@Resource
	private ITeacherService teacherService;
	
	@Resource
	private IAdminstratorService adminstratorService;

	public LoginInformation checkLoginInformation(LoginInformatin4Bean bean) {
		return loginInformationService.checkLoginInformation(bean);
	}

	@RequestMapping("getIndexPage")
	public ModelAndView getIndexPage(Integer id, String account,
			String password, Integer loginType, HttpSession session) {
		LoginInformation loginInformation = new LoginInformation();
		loginInformation.setId(id);
		loginInformation.setAccount(account);
		loginInformation.setPassword(password);
		loginInformation.setLoginType(loginType);

		Map<String, Object> model = new HashMap<String, Object>();
		switch (loginInformation.getLoginType()) {
		case LoginInformation4Enum.STUDENT:
			Student student = studentService.findByAccount(loginInformation
					.getAccount());
			model.put("user", student);
			model.put("identity", "student");
			session.setAttribute("loginInfo", loginInformation);
			break;
		case LoginInformation4Enum.TEACHER:
			Teacher teacher = teacherService.findByAccount(loginInformation
					.getAccount());
			model.put("user", teacher);
			model.put("identity", "teacher");
			session.setAttribute("loginInfo", loginInformation);
			break;
		case LoginInformation4Enum.ADMINISTRATOR:
			Adminstrator adminstrator = adminstratorService.findByAccount(loginInformation.getAccount());
			model.put("user", adminstrator);
			model.put("identity", "adminstrator");
			session.setAttribute("loginInfo", loginInformation);
			break;
		}
		return new ModelAndView("index/index", model);
	}

	@ResponseBody
	@RequestMapping("checkLogin")
	public Result4Json login(
			@ModelAttribute("loginForm") LoginInformatin4Bean bean) {
		LoginInformation loginInformation = checkLoginInformation(bean);
		if (loginInformation == null) {
			return new Result4Json(false, "请输入正确的账号和密码");
		} else {
			return new Result4Json(true, loginInformation);
		}
	}
}
