package com.info.admin.html5.repository;

/**
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.info.admin.html5.entity.Key1;

public interface Key1Repository extends JpaRepository<Key1, Long> {
	Key1 findByName(String name);
}
