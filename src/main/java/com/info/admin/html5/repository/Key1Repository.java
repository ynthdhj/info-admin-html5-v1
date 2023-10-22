package com.info.admin.html5.repository;

/*
 *  @author 段洪杰
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.info.admin.html5.entity.Key1;

public interface Key1Repository extends JpaRepository<Key1, Long> {
	Key1 findByName(String name);
}
