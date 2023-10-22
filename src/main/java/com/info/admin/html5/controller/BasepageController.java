package com.info.admin.html5.controller;

import java.util.Date;

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

import com.info.admin.html5.dto.BasepageDto;
import com.info.admin.html5.entity.Basepage;
import com.info.admin.html5.service.BasepageService;
import com.info.util.page.PageHelper;
import com.info.util.upload.UploadHelper;

import jakarta.validation.Valid;

/*
 *  @author 段洪杰
 */
@Controller

@RequestMapping("/admin")

public class BasepageController {

	private BasepageService basepageService;

	private int size = 5; // 每页记录数

	public BasepageController(BasepageService basepageService) {
		this.basepageService = basepageService;
	}

	@GetMapping("/basepage/add")
	public String basepageAdd(Model model) {
		BasepageDto basepageDto = new BasepageDto();
		model.addAttribute("basepageDto", basepageDto);
		model.addAttribute("uri", "basepage");
		return "basepageAdd";
	}

	@PostMapping("/basepage/save")
	public String basepageSave(@Valid @ModelAttribute("basepageDto") BasepageDto basepageDto, BindingResult result,
			@RequestParam("file") MultipartFile file, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("basepageDto", basepageDto);
			model.addAttribute("uri", "basepage");
			return "basepageAdd";
		}
		String path = basepageService.uploadFile(file, "basepage");

		Basepage basepage = new Basepage();
		basepage.setTitle(basepageDto.getTitle());
		basepage.setMessage(basepageDto.getMessage());
		basepage.setPath(path);
		basepage.setCreatedate(new Date());

		basepageService.save(basepage);

		return "redirect:/admin/basepage/add?success";
	}

	@GetMapping("/basepage/list/{number}")
	public String basepageList(@PathVariable("number") int number, Model model) {
		Sort.TypedSort<Basepage> typedSort = Sort.sort(Basepage.class);
		Sort desc = typedSort.by(Basepage::getId).descending();
		Page<Basepage> page = basepageService.list(PageRequest.of(number - 1, this.size, desc));

		PageHelper pageHelper = new PageHelper("/admin/basepage/list", page);
		model.addAttribute("page", page);
		model.addAttribute("pageHelper", pageHelper);
		model.addAttribute("uri", "basepage");
		return "basepageList";
	}

	@GetMapping("/basepage/view/{id}")
	public String basepageView(@PathVariable(name = "id") Long id, Model model) {
		Basepage basepage = basepageService.view(id);
		model.addAttribute("uri", "basepage");
		if (basepage != null) {
			model.addAttribute("basepage", basepage);
			return "basepageView";
		} else {
			model.addAttribute("message", "记录不存在！");
			model.addAttribute("btn_color_class", "btn btn-success btn-big");
			model.addAttribute("back", "javascript:history.back();");
			return "message";
		}

	}

	@GetMapping("/basepage/edit/{id}")
	public String basepageEdit(@PathVariable(name = "id") Long id,
			@RequestParam("numberOfElements") int numberOfElements, @RequestParam("number") int number, Model model) {
		Basepage basepage = basepageService.edit(id);
		model.addAttribute("uri", "basepage");
		if (basepage != null) {
			BasepageDto basepageDto = new BasepageDto();
			basepageDto.setId(basepage.getId());
			basepageDto.setTitle(basepage.getTitle());
			basepageDto.setMessage(basepage.getMessage());
			basepageDto.setPath(basepage.getPath());
			model.addAttribute("basepageDto", basepageDto);
			model.addAttribute("numberOfElements", numberOfElements);
			model.addAttribute("number", number);
			return "basepageEdit";
		} else {
			model.addAttribute("message", "记录不存在！");
			model.addAttribute("btn_color_class", "btn btn-success btn-big");
			model.addAttribute("back", "javascript:history.back();");
			return "message";
		}
	}

	@PostMapping("/basepage/update/{id}")
	public String basepageUpdate(@Valid @ModelAttribute("basepageDto") BasepageDto basepageDto, BindingResult result,
			@PathVariable(name = "id") Long id, @RequestParam("file") MultipartFile file,
			@RequestParam("number") int number, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("basepageDto", basepageDto);
			model.addAttribute("uri", "basepage");
			return "basepageEdit";
		}

		String path = basepageService.uploadFile(file, "basepage");

		Basepage basepage = basepageService.view(id);
		basepage.setTitle(basepageDto.getTitle());
		basepage.setMessage(basepageDto.getMessage());
		if (path != null)
			basepage.setPath(path);

		basepageService.update(basepage);

		return "redirect:/admin/basepage/list/" + number + "?successUpdate";
	}

	@GetMapping("/basepage/delete/{id}")
	public String basepageDelete(@PathVariable(name = "id") Long id,
			@RequestParam("numberOfElements") int numberOfElements, @RequestParam("number") int number, Model model) {
		basepageService.delete(id);
		model.addAttribute("uri", "basepage");
		// 如果当前页只有一条记录，又删掉一条记录，则当前页为空.如果不是第一页，要转到前一页去显示list。
		if (numberOfElements == 1 && number > 1) {
			number = number - 1;
		}
		return "redirect:/admin/basepage/list/" + number + "?success";

	}
}
