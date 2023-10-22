package com.info.admin.html5.service;

import java.util.List;

import com.info.admin.html5.dto.BoardDto;
import com.info.admin.html5.entity.Admin;
import com.info.admin.html5.entity.Board;

/*
 *  @author 段洪杰
 */
public interface BoardService {
    void save(BoardDto boardDto);
    Board findByName(String name);
    List<Board> findAll();
	void delete(Long id);
}
