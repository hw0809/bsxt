package com.system.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.system.controller.base.BaseController;
import com.system.dao.impl.TestDao;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

	@Resource
	private TestDao testDao;

	@RequestMapping("test")
	public void test() {
		System.out.println(222);
		testDao.test();
	}
	
}
