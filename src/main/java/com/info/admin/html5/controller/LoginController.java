package com.info.admin.html5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.info.admin.html5.service.AdminService;
import com.info.admin.html5.service.BoardService;

/*
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
@Controller

@RequestMapping("/admin")

public class LoginController {

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	@GetMapping("/dashboard")
	public String dashboardForm(Model model) {
		model.addAttribute("uri", "dashboard");
		return "dashboard";
	}

	

}
