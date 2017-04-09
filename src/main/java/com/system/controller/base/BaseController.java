package com.system.controller.base;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.system.entity.LoginInformation;
import com.system.entity.Student;
import com.system.entity.Teacher;
import com.system.entity4enum.LoginInformation4Enum;
import com.system.service.IStudentService;
import com.system.service.ITeacherService;

public class BaseController {

	@Resource
	private IStudentService studentService;
	
	@Resource
	private ITeacherService teacherService;

	public Integer getDepartmentCode(HttpSession session) {
		LoginInformation info = (LoginInformation) session
				.getAttribute("loginInfo");
		Integer code = null;
		switch (info.getLoginType()) {
		case LoginInformation4Enum.STUDENT:
			Student student = studentService.findByAccount(info.getAccount());
			code = student.getDepartment().getDepartmentCode();
			break;
		case LoginInformation4Enum.TEACHER:
			Teacher teacher = teacherService.findByAccount(info.getAccount());
			code = teacher.getDepartment().getDepartmentCode();
			break;
		case LoginInformation4Enum.ADMINISTRATOR:
			break;
		}
		return code;
	}

	/**
	 * 当前页
	 * 
	 * @param request
	 * @return
	 */
	public Integer getPage(HttpServletRequest request) {
		if (request.getParameter("page") == null) {
			return 1;
		} else {
			return Integer.valueOf(request.getParameter("page"));
		}
	}

	/**
	 * 一页的行数
	 * 
	 * @param request
	 * @return
	 */
	public Integer getRows(HttpServletRequest request) {
		if (request.getParameter("rows") == null) {
			return 10;
		} else {
			return Integer.valueOf(request.getParameter("rows"));
		}
	}

	/**
	 * 总页数
	 * 
	 * @param rows
	 * @param total
	 * @return
	 */
	public Integer getTotal(Integer rows, Integer total) {
		Integer count = null;
		if (total % rows == 0) {
			count = total / rows;
		} else {
			count = total / rows + 1;
		}
		return count;
	}

}
