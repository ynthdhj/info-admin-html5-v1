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

import com.info.admin.html5.dto.BoardDto;
import com.info.admin.html5.entity.Board;
import com.info.admin.html5.service.BoardService;

import jakarta.validation.Valid;

/*
 *  @author 段洪杰
 */
@Controller

@RequestMapping("/admin")

public class BoardController {

	private BoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping("/board/add")
	public String boardAdd(Model model) {
		BoardDto boardDto = new BoardDto();
		model.addAttribute("boardDto", boardDto);
		model.addAttribute("uri", "board");
		return "boardAdd";
	}

	@PostMapping("/board/save")
	public String boardSave(@Valid @ModelAttribute("boardDto") BoardDto boardDto, BindingResult result, Model model) {

		Board existing = boardService.findByName(boardDto.getName());
		if (existing != null) {
			result.rejectValue("name", null, "已经有此栏目.");
		}
		if (result.hasErrors()) {
			model.addAttribute("boardDto", boardDto);
			model.addAttribute("uri", "board");
			return "boardAdd";
		}
		boardService.save(boardDto);
		return "redirect:/admin/board/add?success";
	}

	@GetMapping("/board/all")
	public String boardAll(Model model) {
		List<Board> boards = boardService.findAll();
		model.addAttribute("boards", boards);
		model.addAttribute("uri", "board");
		return "boardAll";
	}

	@GetMapping("/board/delete/{id}")
	public String boardDelete(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("uri", "board");
		boardService.delete(id);
		return "redirect:/admin/board/all?success";

	}
}
