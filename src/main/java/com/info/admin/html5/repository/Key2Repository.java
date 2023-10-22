package com.info.admin.html5.repository;

/*
 *  @author 段洪杰
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.info.admin.html5.entity.Key2;

public interface Key2Repository extends JpaRepository<Key2, Long> {
	Key2 findByName(String name);
}
