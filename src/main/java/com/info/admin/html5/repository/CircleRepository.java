package com.info.admin.html5.repository;

/*
 *  @author 段洪杰
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.info.admin.html5.entity.Circle;

public interface CircleRepository extends JpaRepository<Circle, Long> {
	Circle findByName(String name);
}
