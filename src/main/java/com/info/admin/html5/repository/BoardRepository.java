package com.info.admin.html5.repository;

/*
 *  @author 段洪杰
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.info.admin.html5.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	Board findByName(String name);
}
