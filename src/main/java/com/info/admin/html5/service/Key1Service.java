package com.info.admin.html5.service;

import java.util.List;

import com.info.admin.html5.dto.Key1Dto;
import com.info.admin.html5.entity.Admin;
import com.info.admin.html5.entity.Key1;

/**
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
public interface Key1Service {
    void save(Key1Dto key1Dto);
    Key1 findByName(String name);
    List<Key1> findAll();
	void delete(Long id);
}
