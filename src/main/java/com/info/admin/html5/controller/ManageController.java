package com.info.admin.html5.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.info.admin.html5.dto.ManageDto;
import com.info.admin.html5.entity.Manage;
import com.info.admin.html5.service.ManageService;
import com.info.util.page.PageHelper;

import jakarta.validation.Valid;

/**
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
@Controller

@RequestMapping("/admin")

public class ManageController {
	
	private int size = 5; // 每页记录数

	private ManageService manageService;

	public ManageController(ManageService manageService) {
		this.manageService = manageService;
	}

	@GetMapping("/manage/add")
	public String manageAdd(Model model) {
		ManageDto manageDto = new ManageDto();
		model.addAttribute("manageDto", manageDto);
		model.addAttribute("uri", "manage");
		return "manageAdd";
	}

	@PostMapping("/manage/save")
	public String manageSave(@Valid @ModelAttribute("manageDto") ManageDto manageDto, BindingResult result, Model model) {

		Manage existing = manageService.findByPhone(manageDto.getPhone());
		if (existing != null) {
			result.rejectValue("phone", null, "此电话用户已经存在.");
		}
		if (result.hasErrors()) {
			model.addAttribute("manageDto", manageDto);
			model.addAttribute("uri", "manage");
			return "manageAdd";
		}
		manageService.save(manageDto); //在 ManageServiceImpl 中加密 password
		return "redirect:/admin/manage/add?success";
	}

	@GetMapping("/manage/list/{number}")
	public String manageList(@PathVariable("number") int number, Model model) {
		Sort.TypedSort<Manage> typedSort = Sort.sort(Manage.class);
		Sort desc = typedSort.by(Manage::getId).descending();
		Page<Manage> page = manageService.list(PageRequest.of(number - 1, this.size, desc));

		PageHelper pageHelper = new PageHelper("/admin/manage/list", page);
		model.addAttribute("page", page);
		model.addAttribute("pageHelper", pageHelper);
		model.addAttribute("uri", "manage");
		return "manageList";
	}

	@GetMapping("/manage/view/{id}")
	public String manageView(@PathVariable(name = "id") Long id, Model model) {
		Manage manage = manageService.view(id);
		model.addAttribute("uri", "manage");
		if (manage != null) {
			model.addAttribute("manage", manage);
			return "manageView";
		} else {
			model.addAttribute("message", "记录不存在！");
			model.addAttribute("btn_color_class", "btn btn-success btn-big");
			model.addAttribute("back", "javascript:history.back();");
			return "message";
		}

	}

	@GetMapping("/manage/edit/{id}")
	public String manageEdit(@PathVariable(name = "id") Long id,
			@RequestParam("numberOfElements") int numberOfElements, @RequestParam("number") int number, Model model) {
		Manage manage = manageService.edit(id);
		model.addAttribute("uri", "manage");
		if (manage != null) {
			ManageDto manageDto = new ManageDto();
			manageDto.setId(manage.getId());
			manageDto.setPhone(manage.getPhone()); 
			manageDto.setName(manage.getName());
			manageDto.setMessage(manage.getMessage());
			 
			model.addAttribute("manageDto", manageDto);
			model.addAttribute("numberOfElements", numberOfElements);
			model.addAttribute("number", number);
			return "manageEdit";
		} else {
			model.addAttribute("message", "记录不存在！");
			model.addAttribute("btn_color_class", "btn btn-success btn-big");
			model.addAttribute("back", "javascript:history.back();");
			return "message";
		}
	}
	
	@GetMapping("/manage/editPassword/{id}")
	public String manageEditPassword(@PathVariable(name = "id") Long id,
			@RequestParam("numberOfElements") int numberOfElements, @RequestParam("number") int number, Model model) {
		Manage manage = manageService.editPassword(id);
		model.addAttribute("uri", "manage");
		if (manage != null) {
			ManageDto manageDto = new ManageDto();
			manageDto.setId(manage.getId());
			manageDto.setPhone(manage.getPhone()); 
			manageDto.setName(manage.getName());
						 
			model.addAttribute("manageDto", manageDto);
			model.addAttribute("numberOfElements", numberOfElements);
			model.addAttribute("number", number);
			return "manageEditPassword";
		} else {
			model.addAttribute("message", "记录不存在！");
			model.addAttribute("btn_color_class", "btn btn-success btn-big");
			model.addAttribute("back", "javascript:history.back();");
			return "message";
		}
	}

	@PostMapping("/manage/update/{id}")
	public String manageUpdate(@Valid @ModelAttribute("manageDto") ManageDto manageDto, BindingResult result,
			@PathVariable(name = "id") Long id,	@RequestParam("number") int number, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("manageDto", manageDto);
			model.addAttribute("uri", "manage");
			return "manageEdit";
		}

		Manage manage = manageService.view(id);
		// phone 不能修改
		// password 不能修改，要单独重置
		manage.setName(manageDto.getName());
		manage.setMessage(manageDto.getMessage());
	
		manageService.update(manage);

		return "redirect:/admin/manage/list/" + number + "?successUpdate";
	}
	
	@PostMapping("/manage/updatePassword/{id}")
	public String manageUpdatePassword(@Valid @ModelAttribute("manageDto") ManageDto manageDto, BindingResult result,
			@PathVariable(name = "id") Long id,	@RequestParam("number") int number, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("manageDto", manageDto);
			model.addAttribute("uri", "manage");
			return "manageEditPassword";
		}

		Manage manage = manageService.editPassword(id);
		 
		manage.setPassword(manageDto.getPassword());//在 ManageServiceImpl 中加密 password
		 
		manageService.updatePassword(manage);

		return "redirect:/admin/manage/list/" + number + "?successUpdate";
	}

	@GetMapping("/manage/delete/{id}")
	public String manageDelete(@PathVariable(name = "id") Long id,
			@RequestParam("numberOfElements") int numberOfElements, @RequestParam("number") int number, Model model) {
		manageService.delete(id);
		model.addAttribute("uri", "manage");
		// 如果当前页只有一条记录，又删掉一条记录，则当前页为空.如果不是第一页，要转到前一页去显示list。
		if (numberOfElements == 1 && number > 1) {
			number = number - 1;
		}
		return "redirect:/admin/manage/list/" + number + "?success";

	}
}
