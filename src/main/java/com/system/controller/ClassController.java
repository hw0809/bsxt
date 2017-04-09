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
import com.system.entity.Classes;
import com.system.entity.Department;
import com.system.entity.LoginInformation;
import com.system.entity4Json.Classes4Json;
import com.system.entity4Json.Department4Json;
import com.system.entity4Json.Result4Json;
import com.system.jqgriddata.JQGridResult;
import com.system.service.IAdminstratorService;
import com.system.service.IClassesService;
import com.system.service.IDepartmentService;
import com.system.util.ImportExcelUtil;
import com.system.util.StringUtil;

@Controller
@RequestMapping("/classes")
public class ClassController extends BaseController {

	@Resource
	private IClassesService classesService;

	@Resource
	private IDepartmentService departmentService;

	@Resource
	private IAdminstratorService adminstratorService;

	@RequestMapping("getPage")
	public ModelAndView getPage() {
		return new ModelAndView("classes/classes_manage");
	}

	@RequestMapping("getPageList")
	public @ResponseBody
	JQGridResult<Classes4Json> getPageList(String account, String name,
			String classes, HttpServletRequest request, HttpSession session) {
		Classes c = new Classes();
		if (classes != null && !StringUtil.isNullOrEmpty(classes)) {
			c.setName(classes);
		}
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String a = loginInfo.getAccount();
		Adminstrator adminstrator = adminstratorService.findByAccount(a);
		Integer departmentId = adminstrator.getDepartment().getId();
		List<Classes> classesList = classesService.getPageList(c,
				super.getPage(request), super.getRows(request), departmentId);
		Integer total = classesService.getPageTotal(c, departmentId);
		// Integer total = classesService.getPageTotal();
		List<Classes4Json> jsons = new ArrayList<Classes4Json>();
		Classes4Json json = null;
		for (Classes item : classesList) {
			json = new Classes4Json();
			json.setId(item.getId());
			json.setName(item.getName());
			json.setDepartment(item.getDepartment().getName());
			jsons.add(json);
		}

		JQGridResult<Classes4Json> rs = new JQGridResult<Classes4Json>();
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
		return new ModelAndView("classes/classes_add", model);
	}

	@RequestMapping("departmentsList")
	public @ResponseBody
	Department4Json getDepartmentsList(Integer code, HttpSession session) {
		Department dept = departmentService.getDepartmentByCode(code);
		Department4Json json = new Department4Json();
		if (dept != null) {
			json.setDepartmentCode(dept.getDepartmentCode());
			json.setName(dept.getName());
		}
		return json;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json saveClasses(String name, Integer departmentCode, Integer grade) {
		Classes c = classesService.findByName(name);
		if (c != null) {
			return new Result4Json(false, "已经存在该班级");
		}

		Department department = departmentService
				.getDepartmentByCode(departmentCode);
		if (department == null) {
			return new Result4Json(false, "系别出错,请联系管理员!");
		}
		Classes classes = new Classes();
		classes.setName(name);
		classes.setDepartment(department);

		/*
		 * Calendar calendar = Calendar.getInstance(); Integer grade =
		 * calendar.get(Calendar.YEAR);
		 */
		classes.setGrade(grade);
		try {
			classesService.save(classes);
			return new Result4Json(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result4Json(false, "保存失败");
		}
	}

	@RequestMapping(value = "eidt", method = RequestMethod.GET)
	public ModelAndView edit(Integer id) {
		Classes classes = classesService.findById(id);
		if (classes == null) {

		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("classes", classes);
		return new ModelAndView("classes/classes_edit", model);
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json modify(String id, String name, Integer departmentCode,
			Integer grade) {
		Classes old = classesService.findById(Integer.valueOf(id));
		if (old == null) {
			return new Result4Json(false, "该班级信息不存在");
		}
		old.setName(name);

		Department department = departmentService
				.getDepartmentByCode(departmentCode);
		old.setDepartment(department);

		old.setGrade(grade);

		try {
			classesService.modify(old);
			return new Result4Json(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result4Json(false, "保存失败");
		}
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json saveStudent(@RequestParam("ids[]") List<Integer> ids) {
		try {
			for (Integer id : ids) {
				classesService.deleteById(id);
			}
			return new Result4Json(true, "删除成功");
		} catch (Exception e) {
			return new Result4Json(false, "删除失败");
		}
	}

	/**
	 * 上傳班级excel
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
		for (int i = 0; i < listob.size(); i++) {
			List<Object> lo = listob.get(i);

			Classes classes = new Classes();
			classes.setName(String.valueOf(lo.get(0)));
			classes.setGrade(Integer.valueOf((String) lo.get(1)));
			classes.setDepartment(department);

			classesService.save(classes);
		}

		return new Result4Json(true);
	}

}
