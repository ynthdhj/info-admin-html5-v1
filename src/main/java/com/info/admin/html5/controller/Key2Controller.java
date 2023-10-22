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

import com.info.admin.html5.dto.Key2Dto;
import com.info.admin.html5.entity.Key2;
import com.info.admin.html5.service.Key2Service;

import jakarta.validation.Valid;

/*
 *  @author 段洪杰
 */
@Controller

@RequestMapping("/admin")

public class Key2Controller {

	private Key2Service key2Service;

	public Key2Controller(Key2Service key2Service) {
		this.key2Service = key2Service;
	}

	@GetMapping("/key2/add")
	public String key2Add(Model model) {
		Key2Dto key2Dto = new Key2Dto();
		model.addAttribute("key2Dto", key2Dto);
		model.addAttribute("uri", "key2");
		return "key2Add";
	}

	@PostMapping("/key2/save")
	public String key2Save(@Valid @ModelAttribute("key2Dto") Key2Dto key2Dto, BindingResult result, Model model) {

		Key2 existing = key2Service.findByName(key2Dto.getName());
		if (existing != null) {
			result.rejectValue("name", null, "已经有此分类2.");
		}
		if (result.hasErrors()) {
			model.addAttribute("key2Dto", key2Dto);
			model.addAttribute("uri", "key2");
			return "key2Add";
		}
		key2Service.save(key2Dto);
		return "redirect:/admin/key2/add?success";
	}

	@GetMapping("/key2/all")
	public String key2All(Model model) {
		List<Key2> key2s = key2Service.findAll();
		model.addAttribute("key2s", key2s);
		model.addAttribute("uri", "key2");
		return "key2All";
	}

	@GetMapping("/key2/delete/{id}")
	public String key2Delete(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("uri", "key2");
		key2Service.delete(id);
		return "redirect:/admin/key2/all?success";

	}
}
