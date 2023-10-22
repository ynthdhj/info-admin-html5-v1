package com.info.admin.html5.service;

import java.util.List;

import com.info.admin.html5.dto.BoardDto;
import com.info.admin.html5.entity.Admin;
import com.info.admin.html5.entity.Board;

/*
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
public interface BoardService {
    void save(BoardDto boardDto);
    Board findByName(String name);
    List<Board> findAll();
	void delete(Long id);
}
