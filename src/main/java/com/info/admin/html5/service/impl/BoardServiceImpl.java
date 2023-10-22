package com.info.admin.html5.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.info.admin.html5.dto.BoardDto;
import com.info.admin.html5.entity.Board;
import com.info.admin.html5.repository.BoardRepository;
import com.info.admin.html5.service.BoardService;

/**
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
@Service
public class BoardServiceImpl implements BoardService {

    private BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;     
    }


    public void save(BoardDto boardDto) { 
    	Board board = new Board();
    	board.setName(boardDto.getName());
    	board.setCreatedate(new Date());
    	boardRepository.save(board);
    }
    
    public Board findByName(String name) {
        return boardRepository.findByName(name);
    }


    
    public List<Board> findAll() {
        List<Board> boards = boardRepository.findAll();
        return boards;
    }
   
    public void delete(Long id) {
    	 boardRepository.deleteById(id);
    }

   

   
}
