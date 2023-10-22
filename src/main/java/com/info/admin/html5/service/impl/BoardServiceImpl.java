package com.info.admin.html5.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.info.admin.html5.dto.BoardDto;
import com.info.admin.html5.entity.Board;
import com.info.admin.html5.repository.BoardRepository;
import com.info.admin.html5.service.BoardService;

/*
 * @author 段洪杰
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
