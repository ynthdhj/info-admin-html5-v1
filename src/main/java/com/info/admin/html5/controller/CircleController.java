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

import com.info.admin.html5.dto.CircleDto;
import com.info.admin.html5.entity.Circle;
import com.info.admin.html5.service.CircleService;

import jakarta.validation.Valid;

/*
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
@Controller

@RequestMapping("/admin")

public class CircleController {

	private CircleService circleService;

	public CircleController(CircleService circleService) {
		this.circleService = circleService;
	}

	@GetMapping("/circle/add")
	public String circleAdd(Model model) {
		CircleDto circleDto = new CircleDto();
		model.addAttribute("circleDto", circleDto);
		model.addAttribute("uri", "circle");
		return "circleAdd";
	}

	@PostMapping("/circle/save")
	public String circleSave(@Valid @ModelAttribute("circleDto") CircleDto circleDto, BindingResult result, Model model) {

		Circle existing = circleService.findByName(circleDto.getName());
		if (existing != null) {
			result.rejectValue("name", null, "已经有此圈子.");
		}
		if (result.hasErrors()) {
			model.addAttribute("circleDto", circleDto);
			model.addAttribute("uri", "circle");
			return "circleAdd";
		}
		circleService.save(circleDto);
		return "redirect:/admin/circle/add?success";
	}

	@GetMapping("/circle/all")
	public String circleAll(Model model) {
		List<Circle> circles = circleService.findAll();
		model.addAttribute("circles", circles);
		model.addAttribute("uri", "circle");
		return "circleAll";
	}

	@GetMapping("/circle/delete/{id}")
	public String circleDelete(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("uri", "circle");
		circleService.delete(id);
		return "redirect:/admin/circle/all?success";

	}
}
