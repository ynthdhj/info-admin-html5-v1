package com.info.admin.html5.service;

import java.util.List;

import com.info.admin.html5.dto.Key0Dto;
import com.info.admin.html5.entity.Admin;
import com.info.admin.html5.entity.Key0;

/*
 *  @author 段洪杰
 */
public interface Key0Service {
    void save(Key0Dto key0Dto);
    Key0 findByName(String name);
    List<Key0> findAll();
	void delete(Long id);
}
