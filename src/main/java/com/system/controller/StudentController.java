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
import com.system.entity.Student;
import com.system.entity.Teacher;
import com.system.entity4Json.Classes4Json;
import com.system.entity4Json.Department4Json;
import com.system.entity4Json.Result4Json;
import com.system.entity4Json.StudentInfo4Json;
import com.system.entity4enum.LoginInformation4Enum;
import com.system.jqgriddata.JQGridResult;
import com.system.service.IAdminstratorService;
import com.system.service.IClassesService;
import com.system.service.IDepartmentService;
import com.system.service.ILoginInformationService;
import com.system.service.IStudentService;
import com.system.service.ITeacherService;
import com.system.util.ImportExcelUtil;
import com.system.util.StringUtil;

@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {

	@Resource
	private ILoginInformationService loginInformationService;

	@Resource
	private IStudentService studentService;

	@Resource
	private ITeacherService teacherService;

	@Resource
	private IClassesService classesService;

	@Resource
	private IDepartmentService departmentService;

	@Resource
	private IAdminstratorService adminstratorService;

	@RequestMapping("/getTest2")
	public void getTest2() {
		Student student = new Student();
		student.setAccount("1340328155");
		student.setName("CY");
		student.setGrade(2016);
		Department department = departmentService.findById(1);
		Teacher teacher = teacherService.findById(1);
		student.setDepartment(department);
		student.setTeacher(teacher);
		studentService.save(student);
	}

	@RequestMapping("getPageList")
	public @ResponseBody
	JQGridResult<StudentInfo4Json> getPageList(String account, String name,
			String classes, HttpServletRequest request, HttpSession session) {
		Student student = new Student();
		if (account != null && !StringUtil.isNullOrEmpty(account)) {
			student.setAccount(account);
		}

		if (name != null && !StringUtil.isNullOrEmpty(name)) {
			student.setName(name);
		}

		if (classes != null && !StringUtil.isNullOrEmpty(classes)) {
			Classes c = classesService.findByName(classes);
			if (c == null) {
				c = new Classes();
				c.setId(0);
				c.setName("qbyp");
			}
			student.setClasses(c);
		}

		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String a = loginInfo.getAccount();
		Adminstrator adminstrator = adminstratorService.findByAccount(a);
		Integer departmentId = adminstrator.getDepartment().getId();

		List<Student> students = studentService.getPageList(student,
				super.getPage(request), super.getRows(request), departmentId);

		Integer total = studentService.getPageTotal(student, departmentId);
		StudentInfo4Json json = null;
		List<StudentInfo4Json> list = new ArrayList<StudentInfo4Json>();
		for (Student item : students) {
			json = new StudentInfo4Json();
			json.setId(item.getId());
			json.setAccount(item.getAccount());
			json.setGrade(item.getGrade());
			json.setName(item.getName());
			json.setClassName(item.getClasses().getName());
			list.add(json);

		}
		JQGridResult<StudentInfo4Json> rs = new JQGridResult<StudentInfo4Json>();
		rs.setRows(list);
		rs.setTotal(super.getTotal(super.getRows(request), total));
		rs.setPage(super.getPage(request));
		rs.setRecords(total);
		return rs;

	}

	@RequestMapping("getPage")
	public ModelAndView getPage() {
		return new ModelAndView("student/student_manage");
	}

	@RequestMapping("add")
	public ModelAndView add(HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		LoginInformation loginInfo = (LoginInformation) session
				.getAttribute("loginInfo");
		String account = loginInfo.getAccount();
		Adminstrator adminstrator = adminstratorService.findByAccount(account);
		model.put("code", adminstrator.getDepartment().getDepartmentCode());
		return new ModelAndView("student/student_add", model);
	}

	@RequestMapping("classesList")
	public @ResponseBody
	List<Classes4Json> getClassesList(Integer departmentCode,
			HttpSession session) {
		Department department = departmentService
				.getDepartmentByCode(departmentCode);
		List<Classes> classes = department.getClasses();
		Classes4Json json = null;
		List<Classes4Json> jsons = new ArrayList<Classes4Json>();
		if (classes != null && !classes.isEmpty()) {
			for (Classes item : classes) {
				json = new Classes4Json();
				json.setId(item.getId());
				json.setName(item.getName());
				jsons.add(json);
			}
		}
		return jsons;
	}

	@RequestMapping("departmentsList")
	public @ResponseBody
	List<Department4Json> getDepartmentsList(HttpSession session) {
		List<Department> departments = departmentService.findAll();
		List<Department4Json> jsons = new ArrayList<Department4Json>();
		Department4Json json = null;
		for (Department department : departments) {
			json = new Department4Json();
			json.setDepartmentCode(department.getDepartmentCode());
			json.setName(department.getName());
			jsons.add(json);
		}
		return jsons;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json saveStudent(String account, String name, Integer grade,
			Integer departmentCode, Integer classesId) {
		Student s = studentService.findByAccount(account);
		if (s != null) {
			return new Result4Json(false, "该学号已存在");
		}
		Student student = new Student();
		student.setAccount(account);
		student.setName(name);
		student.setGrade(grade);

		Classes classes = classesService.findById(classesId);
		student.setClasses(classes);

		Department department = departmentService
				.getDepartmentByCode(departmentCode);
		student.setDepartment(department);
		
		LoginInformation info = new LoginInformation();
		info.setLoginType(LoginInformation4Enum.STUDENT);
		info.setPassword("123456");
		info.setAccount(account);

		try {
			studentService.save(student);
			loginInformationService.save(info);
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
				Student student = studentService.findById(id);
				loginInformationService.deleteByAccount(student.getAccount());
				studentService.deleteById(id);
			}
			return new Result4Json(true, "删除成功");
		} catch (Exception e) {
			return new Result4Json(false, "删除失败");
		}
	}

	@RequestMapping(value = "eidt", method = RequestMethod.GET)
	public ModelAndView edit(Integer id) {
		Student student = studentService.findById(id);
		if (student == null) {

		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("student", student);
		return new ModelAndView("student/student_edit", model);
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public @ResponseBody
	Result4Json modify(String account, String name, Integer grade,
			Integer departmentCode, Integer classesId) {
		Student old = studentService.findByAccount(account);
		if (old == null) {
			return new Result4Json(false, "该学号信息不存在");
		}
		old.setName(name);
		old.setGrade(grade);

		Classes classes = classesService.findById(classesId);
		old.setClasses(classes);

		Department department = departmentService
				.getDepartmentByCode(departmentCode);
		old.setDepartment(department);

		try {
			studentService.modify(old);
			return new Result4Json(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result4Json(false, "保存失败");
		}
	}

	/**
	 * 上傳学生excel
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
		String a = loginInfo.getAccount();
		Adminstrator adminstrator = adminstratorService.findByAccount(a);
		
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

			Student student = new Student();
			student.setAccount(String.valueOf(lo.get(0)));
			student.setName(String.valueOf(lo.get(1)));
			student.setGrade(Integer.valueOf((String) lo.get(2)));
			Classes classes = classesService.getClasses(
					Integer.valueOf((String) lo.get(2)),
					String.valueOf(lo.get(3)));
			student.setClasses(classes);
			student.setDepartment(adminstrator.getDepartment());

			LoginInformation info = new LoginInformation();
			info.setAccount(String.valueOf(lo.get(0)));
			info.setPassword("123456");
			info.setLoginType(2);

			loginInformationService.save(info);
			studentService.save(student);

		}

		return new Result4Json(true);
	}
}
