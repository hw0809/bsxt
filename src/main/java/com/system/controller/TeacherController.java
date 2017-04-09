package com.system.controller;

import java.io.InputStream;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.system.controller.base.BaseController;
import com.system.entity.Adminstrator;
import com.system.entity.Department;
import com.system.entity.LoginInformation;
import com.system.entity.Teacher;
import com.system.entity4Json.Result4Json;
import com.system.entity4Json.Teacher4Json;
import com.system.entity4enum.LoginInformation4Enum;
import com.system.jqgriddata.JQGridResult;
import com.system.service.IAdminstratorService;
import com.system.service.IDepartmentService;
import com.system.service.ILoginInformationService;
import com.system.service.ITeacherService;
import com.system.util.ImportExcelUtil;
import com.system.util.StringUtil;

@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController {

	@Resource
	private ITeacherService teacherService;

	@Resource
	private IDepartmentService departmentService;

	@Resource
	private ILoginInformationService loginInformationService;

	@Resource
	private IAdminstratorService adminstratorService;

	@RequestMapping("getPage")
	public ModelAndView getPage() {
		return new ModelAndView("teacher/teacher_manage");
	}

	@RequestMapping("getPageList")
	public @ResponseBody
	JQGridResult<Teacher4Json> getPageList(String account, String name,
			HttpServletRequest request, HttpSession session) {
		Teacher teacher = new Teacher();
		if (account != null && !StringUtil.isNullOrEmpty(account)) {
			teacher.setAccount(account);
		}
		if (name != null && !StringUtil.isNullOrEmpty(name)) {
			teacher.setName(name);
		}
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String a = loginInfo.getAccount();
		Adminstrator adminstrator = adminstratorService.findByAccount(a);
		Integer departmentId = adminstrator.getDepartment().getId();
		List<Teacher> teachers = teacherService.getPageList(teacher,
				super.getPage(request), super.getRows(request), departmentId);
		Integer total = teacherService.getPageTotal(teacher, departmentId);
		List<Teacher4Json> jsons = new ArrayList<Teacher4Json>();
		Teacher4Json json = null;

		for (Teacher item : teachers) {
			json = new Teacher4Json();
			json.setId(item.getId());
			json.setDepartment(item.getDepartment().getName());
			json.setName(item.getName());
			json.setAccount(item.getAccount());
			jsons.add(json);
		}

		JQGridResult<Teacher4Json> rs = new JQGridResult<Teacher4Json>();
		rs.setRows(jsons);
		rs.setTotal(super.getTotal(super.getRows(request), total));
		rs.setPage(super.getPage(request));
		rs.setRecords(total);
		return rs;
	}

	@RequestMapping("add")
	public ModelAndView add(HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Adminstrator adminstrator = adminstratorService.findByAccount(account);
		model.put("code", adminstrator.getDepartment().getDepartmentCode());
		return new ModelAndView("teacher/teacher_add", model);
	}

	@RequestMapping("departmentsList")
	public @ResponseBody
	List<Department> getDepartmentsList(HttpSession session) {
		List<Department> departments = departmentService.findAll();
		return departments;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json saveStudent(String account, String name, Integer departmentCode) {
		Teacher t = teacherService.findByAccount(account);
		if (t != null) {
			return new Result4Json(false, "该工号已存在");
		}
		Teacher teacher = new Teacher();
		teacher.setAccount(account);
		teacher.setName(name);
		Department department = departmentService
				.getDepartmentByCode(departmentCode);
		teacher.setDepartment(department);

		LoginInformation info = new LoginInformation();
		info.setLoginType(LoginInformation4Enum.TEACHER);
		info.setPassword("123456");
		info.setAccount(account);

		try {
			teacherService.save(teacher);
			loginInformationService.save(info);
			return new Result4Json(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result4Json(false, "保存失败");
		}
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json delTeacher(@RequestParam("ids[]") List<Integer> ids) {
		try {
			for (Integer id : ids) {
				Teacher teacher = teacherService.findById(id);
				loginInformationService.deleteByAccount(teacher.getAccount());
				teacherService.deleteById(id);
			}
			return new Result4Json(true, "删除成功");
		} catch (Exception e) {
			return new Result4Json(false, "删除失败");
		}
	}

	@RequestMapping(value = "eidt", method = RequestMethod.GET)
	public ModelAndView edit(Integer id) {
		Teacher teacher = teacherService.findById(id);
		if (teacher == null) {

		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("teacher", teacher);
		return new ModelAndView("teacher/teacher_edit", model);
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json modify(String account, String name, Integer departmentCode) {
		Teacher old = teacherService.findByAccount(account);
		if (old == null) {
			return new Result4Json(false, "该工号信息不存在");
		}
		old.setName(name);

		Department department = departmentService
				.getDepartmentByCode(departmentCode);
		old.setDepartment(department);

		try {
			teacherService.modify(old);
			return new Result4Json(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result4Json(false, "保存失败");
		}
	}

	/**
	 * 上傳教师excel
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "uploadExcel", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json ajaxUploadExcel(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Adminstrator adminstrator = adminstratorService.findByAccount(account);
		Department department = adminstrator.getDepartment();

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		InputStream in = null;
		List<List<Object>> listob = null;
		MultipartFile file = multipartRequest.getFile("upfile");
		if (file.isEmpty()) {
			throw new Exception("文件不存在！");
		}

		in = file.getInputStream();
		listob = new ImportExcelUtil().getBankListByExcel(in, new String(file
				.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8"));
		// 该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
		for (int i = 0; i < listob.size(); i++) {
			List<Object> lo = listob.get(i);

			Teacher teacher = new Teacher();
			teacher.setAccount(String.valueOf(lo.get(0)));
			teacher.setName(String.valueOf(lo.get(1)));
			teacher.setDepartment(department);

			LoginInformation info = new LoginInformation();
			info.setAccount(String.valueOf(lo.get(0)));
			info.setPassword("123456");
			info.setLoginType(1);

			loginInformationService.save(info);
			teacherService.save(teacher);

		}

		return new Result4Json(true);
		/*
		 * PrintWriter out = null; response.setCharacterEncoding("utf-8"); //
		 * 防止ajax接受到的中文信息乱码 out = response.getWriter(); out.print("文件导入成功！");
		 * out.flush(); out.close();
		 */
	}

}
