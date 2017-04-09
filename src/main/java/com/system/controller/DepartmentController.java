package com.system.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.system.controller.base.BaseController;
import com.system.entity.Department;
import com.system.service.IDepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {

	@Resource
	private IDepartmentService departmentService;

	@RequestMapping("/getTest2")
	public void getTest2() {
		System.out.println("class");
		Department department = new Department();
		// department.setId(UUIDGenerator.getUUID());
		department.setDepartmentCode(1005);
		department.setName("多媒体系");
		departmentService.save(department);
	}

}
