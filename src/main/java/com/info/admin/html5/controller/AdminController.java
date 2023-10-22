package com.info.admin.html5.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.info.admin.html5.dto.AdminDto;
import com.info.admin.html5.entity.Admin;
import com.info.admin.html5.service.AdminService;

import jakarta.validation.Valid;

/**
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
@Controller

@RequestMapping("/admin")

public class AdminController {

	private AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping("/admin/add")
	public String adminAdd(Model model) {
		AdminDto adminDto = new AdminDto();
		model.addAttribute("adminDto", adminDto);
		model.addAttribute("uri", "admin");
		return "adminAdd";
	}

	@PostMapping("/admin/save")
	public String adminSave(@Valid @ModelAttribute("adminDto") AdminDto adminDto, BindingResult result, Model model) {

		Admin existing = adminService.findByUsername(adminDto.getUsername());
		if (existing != null) {
			result.rejectValue("username", null, "已经有此用户.");
		}
		if (result.hasErrors()) {
			model.addAttribute("adminDto", adminDto);
			model.addAttribute("uri", "admin");
			return "adminAdd";
		}
		adminService.save(adminDto);
		return "redirect:/admin/admin/add?success";
	}

	@GetMapping("/admin/all")

	public String adminAll(Model model) {
		List<Admin> admins = adminService.findAll();
		model.addAttribute("admins", admins);
		model.addAttribute("uri", "admin");
		return "adminAll";
	}

	@GetMapping("/admin/delete/{id}")

	public String adminDelete(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("uri", "admin");
		// 不能删除自已
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName(); // 登录用户自已
		Admin adminEntity = adminService.findByUsername(name);
		if (adminEntity.getId().equals(id)) {
			// 不能删自已
			return "redirect:/admin/admin/all?error";
		} else {
			adminService.delete(id);
			return "redirect:/admin/admin/all?success";
		}
	}
}
