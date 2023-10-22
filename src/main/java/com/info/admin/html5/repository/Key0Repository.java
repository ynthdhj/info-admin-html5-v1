package com.info.admin.html5.repository;

/*
 *  @author 段洪杰
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.info.admin.html5.entity.Key0;

public interface Key0Repository extends JpaRepository<Key0, Long> {
	Key0 findByName(String name);
}
