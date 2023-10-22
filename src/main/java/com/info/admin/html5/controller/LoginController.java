package com.info.admin.html5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.info.admin.html5.service.AdminService;
import com.info.admin.html5.service.BoardService;

/*
 *  @author 段洪杰
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
