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

import com.info.admin.html5.dto.Key1Dto;
import com.info.admin.html5.entity.Key1;
import com.info.admin.html5.service.Key1Service;

import jakarta.validation.Valid;

/*
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
@Controller

@RequestMapping("/admin")

public class Key1Controller {

	private Key1Service key1Service;

	public Key1Controller(Key1Service key1Service) {
		this.key1Service = key1Service;
	}

	@GetMapping("/key1/add")
	public String key1Add(Model model) {
		Key1Dto key1Dto = new Key1Dto();
		model.addAttribute("key1Dto", key1Dto);
		model.addAttribute("uri", "key1");
		return "key1Add";
	}

	@PostMapping("/key1/save")
	public String key1Save(@Valid @ModelAttribute("key1Dto") Key1Dto key1Dto, BindingResult result, Model model) {

		Key1 existing = key1Service.findByName(key1Dto.getName());
		if (existing != null) {
			result.rejectValue("name", null, "已经有此分类1.");
		}
		if (result.hasErrors()) {
			model.addAttribute("key1Dto", key1Dto);
			model.addAttribute("uri", "key1");
			return "key1Add";
		}
		key1Service.save(key1Dto);
		return "redirect:/admin/key1/add?success";
	}

	@GetMapping("/key1/all")
	public String key1All(Model model) {
		List<Key1> key1s = key1Service.findAll();
		model.addAttribute("key1s", key1s);
		model.addAttribute("uri", "key1");
		return "key1All";
	}

	@GetMapping("/key1/delete/{id}")
	public String key1Delete(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("uri", "key1");
		key1Service.delete(id);
		return "redirect:/admin/key1/all?success";

	}
}
