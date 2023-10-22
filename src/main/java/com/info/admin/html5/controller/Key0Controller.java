package com.info.admin.html5.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.info.admin.html5.dto.Key0Dto;
import com.info.admin.html5.entity.Key0;
import com.info.admin.html5.service.Key0Service;

import jakarta.validation.Valid;

/*
 *  @author 段洪杰
 */
@Controller

@RequestMapping("/admin")

public class Key0Controller {

	private Key0Service key0Service;

	public Key0Controller(Key0Service key0Service) {
		this.key0Service = key0Service;
	}

	@GetMapping("/key0/add")
	public String key0Add(Model model) {
		Key0Dto key0Dto = new Key0Dto();
		model.addAttribute("key0Dto", key0Dto);
		model.addAttribute("uri", "key0");
		return "key0Add";
	}

	@PostMapping("/key0/save")
	public String key0Save(@Valid @ModelAttribute("key0Dto") Key0Dto key0Dto, BindingResult result, Model model) {

		Key0 existing = key0Service.findByName(key0Dto.getName());
		if (existing != null) {
			result.rejectValue("name", null, "已经有此分类0.");
		}
		if (result.hasErrors()) {
			model.addAttribute("key0Dto", key0Dto);
			model.addAttribute("uri", "key0");
			return "key0Add";
		}
		key0Service.save(key0Dto);
		return "redirect:/admin/key0/add?success";
	}

	@GetMapping("/key0/all")
	public String key0All(Model model) {
		List<Key0> key0s = key0Service.findAll();
		model.addAttribute("key0s", key0s);
		model.addAttribute("uri", "key0");
		return "key0All";
	}

	@GetMapping("/key0/delete/{id}")
	public String key0Delete(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("uri", "key0");
		key0Service.delete(id);
		return "redirect:/admin/key0/all?success";

	}
}
