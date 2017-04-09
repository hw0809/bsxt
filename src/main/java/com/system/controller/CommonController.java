package com.system.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.system.controller.base.BaseController;

@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {

	@RequestMapping("getNoticeIndex")
	public ModelAndView getIndex() {
		return new ModelAndView("notice/notice");
	}
}
