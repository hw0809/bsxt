package com.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.system.controller.base.BaseController;

@Controller
public class LoginController extends BaseController {

	@RequestMapping("login")
	public ModelAndView getLoginPageAndView() {
		return new ModelAndView("login/login");
	}

}
